package rest;

import game.CurrentGameCreator;
import game.GameHelper;
import game.to.RozsypankaGameStudentTO;
import game.to.RozsypankaGameTO;
import game.to.RozsypankaGameTOs;
import game.to.SolutionTO;
import game.to.TOsManager;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import spring.BeanHelper;
import spring.game.GameManager;
import spring.student.StudentManager;
import dto.Game;
import dto.games.RozsypankaGame;

@Path("game")
public class GameRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    // only for teacher
    @POST
    @Path("/rozsypanka")
    @Produces(MediaType.APPLICATION_JSON)
    public String postRozsypanka(RozsypankaGame rozsypankaGame, @HeaderParam("login") String login)
    {

        gameManager.insertGame(rozsypankaGame, login);

        return "succes";
    }

    // only for teacher
    @GET
    @Path("/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameTO getRozsypankaGame(@PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);
        RozsypankaGameTO rozsypankaGameTO = TOsManager.processRozsypanka(game);

        return rozsypankaGameTO;

    }

    // only for teacher
    @GET
    @Path("/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameTOs getRozsypankaGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, RozsypankaGame.class);
        RozsypankaGameTOs rozsypankas = TOsManager.processRozsypankas(allGames);

        return rozsypankas;

    }

    // only for student
    @GET
    @Path("/student/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameTOs getGamesStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getRozsypankaGames(teacherLogin);

    }

    @GET
    @Path("/student/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameStudentTO getRozsypankaGamex(@HeaderParam("login") String login, @PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);

        RozsypankaGameStudentTO rozsypankaGameStudentTO = CurrentGameCreator.createAndStartCurrRozsypanka(game, login);

        return rozsypankaGameStudentTO;

    }

    @POST
    @Path("/student/check/{noumberOfTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPartOfGame(SolutionTO solution, @PathParam("noumberOfTask") int noumberOfTask, @HeaderParam("login") String login)
    {

        Boolean correct = GameHelper.check(login, noumberOfTask, solution);

        return correct.toString();
    }
}
