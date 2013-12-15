package rest;

import game.CurrentGameCreator;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.wordsearch.WordSearchGameTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Game;
import dto.games.MillionaireGame;
import dto.games.WordSearchGame;
import rest.auth.Role;
import spring.BeanHelper;
import spring.game.GameManager;
import spring.student.StudentManager;

@Path("game")
public class MillionaireRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public MillionaireRest()
    {
        super();
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaire")
    @Produces(MediaType.APPLICATION_JSON)
    public String postMillionaire(MillionaireGameTO millionaireGameTO, @HeaderParam("login") String login)
    {

        MillionaireGame millionaireGame = TOsGameManager.convertMillionaireGameTO(millionaireGameTO);
        gameManager.insertGame(millionaireGame, login);

        return "succes";
    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/millionaire/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MillionaireGameTO getWordSearchGame(@PathParam("id") int id)
    {

        MillionaireGame millionaireGame = gameManager.getMillionaireByID(id);
        MillionaireGameTO millionaireGameTO = TOsGameManager.convertMillionaireGame(millionaireGame);

        return millionaireGameTO;

    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/millionaires")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getWordSearchGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, MillionaireGame.class);
        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/millionaires")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getWordSearchGamesStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getWordSearchGames(teacherLogin);

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/millionaire/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MillionaireGameTO getWordSearchGameStudent(@HeaderParam("login") String login, @PathParam("id") int id)
    {
        MillionaireGame millionaireGame = gameManager.getMillionaireByID(id);
        MillionaireGameTO millionaireGameTO = CurrentGameCreator.createAndStartCurrMillionaire(millionaireGame, login);

        return millionaireGameTO;

    }

}
