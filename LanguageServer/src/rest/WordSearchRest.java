package rest;

import game.CurrentGameCreator;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.wordsearch.WordSearchGameStudentTO;
import game.to.wordsearch.WordSearchGameTO;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import dto.games.WordSearchGame;

@Path("game")
public class WordSearchRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/wordsearch")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer postWordSearch(WordSearchGameTO searchGameTO, @HeaderParam("login") String login)
    {
        WordSearchGame wordSearchGame = TOsGameManager.covertWordSearchGameTO(searchGameTO);

        Integer id = gameManager.insertGame(wordSearchGame, login);

        return id;
    }

    // only for teacher
    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/wordsearch/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WordSearchGameTO getWordSearchGame(@PathParam("id") int id)
    {

        WordSearchGame wordSearchGame = gameManager.getWordSearchByID(id);
        WordSearchGameTO wordSearchGameTO = TOsGameManager.convertSearchGame(wordSearchGame);

        return wordSearchGameTO;

    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/wordsearches")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getWordSearchGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, WordSearchGame.class);
        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    // only for student
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/wordsearches")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getGamesStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getWordSearchGames(teacherLogin);

    }

    @GET
    @Path("/student/wordsearch/{id}/width/{width}/height/{height}")
    @RolesAllowed({ Role.STUDENT })
    @Produces(MediaType.APPLICATION_JSON)
    public WordSearchGameStudentTO getRozsypankaGamex(@HeaderParam("login") String login,
                                                      @PathParam("id") int id,
                                                      @PathParam("width") int width,
                                                      @PathParam("height") int height)
    {

        WordSearchGame wordSearchGame = gameManager.getWordSearchByID(id);

        WordSearchGameStudentTO wordSearchGameStudentTO = CurrentGameCreator.createAndStartCurrWordSearch(wordSearchGame, login, width, height);

        return wordSearchGameStudentTO;

    }

}
