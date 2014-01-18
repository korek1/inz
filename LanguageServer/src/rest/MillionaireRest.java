package rest;

import game.CurrentGameCreator;
import game.GameHelper;
import game.GameRest;
import game.impl.CurrentMillionaireGame;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.millionaire.MillionaireGameTO;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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
import dto.games.MillionaireGame;

@Path("game")
public class MillionaireRest implements GameRest<MillionaireGameTO, MillionaireGameTO> {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public MillionaireRest()
    {
        super();
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaire")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer insertGame(MillionaireGameTO millionaireGameTO, @HeaderParam("login") String login)
    {

        MillionaireGame millionaireGame = TOsGameManager.convertMillionaireGameTO(millionaireGameTO);
        Integer id = gameManager.insertGame(millionaireGame, login);

        return id;
    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaire/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MillionaireGameTO getGame(@PathParam("id") int id)
    {

        MillionaireGame millionaireGame = gameManager.getMillionaireByID(id);
        MillionaireGameTO millionaireGameTO = TOsGameManager.convertMillionaireGame(millionaireGame);

        return millionaireGameTO;

    }
    
    @DELETE
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaire/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteGame(@PathParam("id") int id)
    {

        gameManager.delete(id);

        return "ok";

    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaires")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, MillionaireGame.class);
        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/millionaires")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGamesForStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getAllGames(teacherLogin);

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/millionaire/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MillionaireGameTO getGameForStudent(@HeaderParam("login") String login, @PathParam("id") int id)
    {
        MillionaireGame millionaireGame = gameManager.getMillionaireByID(id);
        MillionaireGameTO millionaireGameTO = CurrentGameCreator.createAndStartCurrMillionaire(millionaireGame, login);

        return millionaireGameTO;

    }

    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/millionaire/help/{question}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getLifeline(@HeaderParam("login") String login, @PathParam("question") int question, int type)
    {

        CurrentMillionaireGame currentMillionaireGame = (CurrentMillionaireGame) GameHelper.getCurrGame(login);

        return currentMillionaireGame.getLifeline(type, question);

    }

}
