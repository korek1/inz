package rest;

import game.CurrentGameCreator;
import game.to.GameTOs;
import game.to.TOsManager;
import game.to.wordsearch.WordSearchGameStudentTO;
import game.to.wordsearch.WordSearchGameTO;

import java.util.List;

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
import dto.games.WordSearchGame;

@Path("game")
public class WordSearchRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    @POST
    @Path("/wordsearch")
    @Produces(MediaType.APPLICATION_JSON)
    public String postWordSearch(WordSearchGameTO searchGameTO, @HeaderParam("login") String login)
    {

        WordSearchGame wordSearchGame = TOsManager.covertWordSearchGameTO(searchGameTO);

        gameManager.insertGame(wordSearchGame, login);

        return "succes";
    }

    // only for teacher
    @GET
    @Path("/wordsearch/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WordSearchGameTO getWordSearchGame(@PathParam("id") int id)
    {

        WordSearchGame wordSearchGame = gameManager.getWordSearchByID(id);
        WordSearchGameTO wordSearchGameTO = TOsManager.convertSearchGame(wordSearchGame);

        return wordSearchGameTO;

    }

    // only for teacher
    @GET
    @Path("/wordsearches")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getWordSearchGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, WordSearchGame.class);
        GameTOs gameTOs = TOsManager.processGames(allGames);

        return gameTOs;

    }

    // only for student
    @GET
    @Path("/student/wordsearches")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getGamesStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getWordSearchGames(teacherLogin);

    }

    @GET
    @Path("/student/wordsearch/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WordSearchGameStudentTO getRozsypankaGamex(@HeaderParam("login") String login, @PathParam("id") int id)
    {

        WordSearchGame wordSearchGame = gameManager.getWordSearchByID(id);
        WordSearchGameTO wordSearchGameTO = TOsManager.convertSearchGame(wordSearchGame);

        WordSearchGameStudentTO wordSearchGameStudentTO = CurrentGameCreator.createAndStartCurrWordSearch(wordSearchGameTO, login);

        return wordSearchGameStudentTO;

    }

}
