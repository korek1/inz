package rest;

import game.CurrentGameCreator;
import game.GameHelper;
import game.data.CurrentRozsypankaTO;
import game.impl.CurrentRozsypankaGame;
import hibernatee.DBUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import spring.teacher.TeacherManager;
import utils.CommonUtils;
import dto.Game;
import dto.Klasa;
import dto.Teacher;
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
    public RozsypankaGame getRozsypankaGame(@PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);
        DBUtils.cleanGame(game);

        return game;

    }

    // only for teacher
    @GET
    @Path("/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getRozsypankaGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, RozsypankaGame.class);
        DBUtils.cleanGames(allGames);

        // samego setu,listy nie chce JSONOWAC a jak collection jest w innym
        // obiekiecie to daje rade (?)
        Teacher teacher = new Teacher();
        teacher.setGames(CommonUtils.ListToSet(allGames));

        return teacher;

    }

    // only for student
    @GET
    @Path("/student/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getGamesStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getRozsypankaGames(teacherLogin);

    }

    @GET
    @Path("/student/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurrentRozsypankaTO getRozsypankaGamex(@HeaderParam("login") String login, @PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);
        
        CurrentRozsypankaGame createCurrRozsypanka = CurrentGameCreator.createCurrRozsypanka(game);
        GameHelper.startGame(login, createCurrRozsypanka);

        CurrentRozsypankaTO currentRozsypankaTO = new CurrentRozsypankaTO();
        currentRozsypankaTO.setData(createCurrRozsypanka.getProcessRozsypanka());

        return currentRozsypankaTO;

    }

    @POST
    @Path("/student/check/{noumberOfTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPartOfGame(CurrentRozsypankaTO dataInThisTask, @PathParam("noumberOfTask") int noumberOfTask, @HeaderParam("login") String login)
    {

        Boolean correct = GameHelper.check(login, noumberOfTask - 1, dataInThisTask.getDataFromStudent());

        return correct.toString();
    }
}
