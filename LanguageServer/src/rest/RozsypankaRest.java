package rest;

import game.CurrentGameCreator;
import game.GameRest;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.rozsypanka.RozsypankaGameStudentTO;
import game.to.rozsypanka.RozsypankaGameTO;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.auth.Role;
import spring.BeanHelper;
import spring.game.GameManager;
import spring.student.StudentManager;
import dto.Game;
import dto.games.RozsypankaGame;

@Path("game")
public class RozsypankaRest implements GameRest<RozsypankaGameTO, RozsypankaGameStudentTO> {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/rozsypanka")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer insertGame(RozsypankaGameTO rozsypankaGameTO, @HeaderParam("login") String login)
    {

        RozsypankaGame rozsypankaGame = TOsGameManager.convertRozsypankaGameTO(rozsypankaGameTO);
        Integer id = gameManager.insertGame(rozsypankaGame, login);

        return id;
    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameTO getGame(@PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);
        RozsypankaGameTO rozsypankaGameTO = TOsGameManager.convertRozsypankaGame(game);

        return rozsypankaGameTO;

    }
    
    @DELETE
    @RolesAllowed({ Role.TEACHER })
    @Path("/rozsypanka/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteGame(@PathParam("id") int id)
    {

        gameManager.delete(id);

        return "ok";

    }

    // only for teacher
    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, RozsypankaGame.class);
        GameTOs games = TOsGameManager.processGames(allGames);

        return games;

    }

    // only for student
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGamesForStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getAllGames(teacherLogin);

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGameStudentTO getGameForStudent(@HeaderParam("login") String login, @PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);

        RozsypankaGameStudentTO rozsypankaGameStudentTO = CurrentGameCreator.createAndStartCurrRozsypanka(game, login);

        return rozsypankaGameStudentTO;

    }

}
