package rest;

import game.CurrentGameCreator;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.hangman.HangManStudentTO;
import game.to.hangman.HangManTO;

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
import dto.games.HangManGame;

@Path("game")
public class HangManRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public HangManRest()
    {
        super();
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/hangman")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer postHangMan(HangManTO hangManTO, @HeaderParam("login") String login)
    {

        HangManGame hangManGame = TOsGameManager.convertHangManGameTO(hangManTO);
        Integer id = gameManager.insertGame(hangManGame, login);

        return id;
    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/hangman/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HangManTO getHangManGame(@PathParam("id") int id)
    {

        HangManGame hangManGame = gameManager.getHangManByID(id);
        HangManTO hangManTO = TOsGameManager.convertHangManGame(hangManGame);

        return hangManTO;

    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/hangmen")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getHangmen(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, HangManGame.class);
        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/hangmen")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getHangmenStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getHangmen(teacherLogin);

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/hangman/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HangManStudentTO getHangManStudent(@HeaderParam("login") String login, @PathParam("id") int id)
    {
        HangManGame hangManGame = gameManager.getHangManByID(id);
        HangManStudentTO hangManStudentTO = CurrentGameCreator.createAndStartCurrHangMan(hangManGame, login);

        return hangManStudentTO;

    }

}
