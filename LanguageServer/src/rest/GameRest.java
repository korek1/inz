package rest;

import game.GameHelper;
import game.helpers.GameTypeEnum;
import game.impl.CurrentHangManGame;
import game.to.SolutionTO;
import game.to.StartedByTeacherTO;
import game.to.TOsGameManager;
import game.to.hangman.LetterPositionTO;

import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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
import spring.gameresult.GameResultManager;
import spring.student.StudentManager;

import com.google.gson.Gson;

import dto.to.GameCategoryTOs;
import dto.to.PointsTO;
import dto.to.gameresult.GameResultClassTOs;
import dto.to.gameresult.GameResultTOs;

@Path("game")
public class GameRest {

    GameResultManager gameResultManager = (GameResultManager) BeanHelper.getBean("gameResultManagerImpl");
    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public GameRest()
    {
        super();
    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public GameCategoryTOs getCategories() throws ParseException
    {
        return TOsGameManager.GAME_CATEGORIES;
    }

    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/check/{noumberOfTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPartOfGame(SolutionTO solution, @PathParam("noumberOfTask") int noumberOfTask, @HeaderParam("login") String login)
    {
        Boolean correct = GameHelper.check(login, noumberOfTask, solution);

        return correct.toString();
    }

    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/hangman/check/{noumberOfTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LetterPositionTO checkPartOfGameHangMan(SolutionTO solution, @PathParam("noumberOfTask") int noumberOfTask, @HeaderParam("login") String login)
    {
        CurrentHangManGame currGame = (CurrentHangManGame) GameHelper.getCurrGame(login);

        checkPartOfGame(solution, noumberOfTask, login);

        return new LetterPositionTO(currGame.getLetterPosition());
    }

    /**
     * Returns student's game history
     * 
     * @param login - teacher login
     * @param idStudent
     * @return
     * @throws ParseException
     */
    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/result/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameResultTOs getGameResult(@HeaderParam("login") String login, @PathParam("id") int idStudent) throws ParseException
    {
        GameResultTOs gameResultTOs = gameResultManager.getStudentsGamesResult(idStudent);

        return gameResultTOs;
    }

    /**
     * Returns concrete game history of given class
     * 
     * @param login
     * @param classID
     * @param gameID
     * @return
     * @throws ParseException
     */
    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/result/class/{classID}/game/{gameID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClassGameResult(@HeaderParam("login") String login, @PathParam("classID") int classID, @PathParam("gameID") int gameID) throws ParseException
    {
        GameResultClassTOs classGameResults = gameResultManager.getClassGameResults(classID, gameID);

        Gson g = new Gson();
        String json = g.toJson(classGameResults);

        return json;
    }

    /**
     * Starts game.
     * 
     * @param login
     * @param gameID
     * @return
     */
    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/start/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String startGameForStudents(@HeaderParam("login") String login, @PathParam("id") int gameID)
    {

        GameTypeEnum type = gameManager.getType(gameID);
        GameHelper.startGameTeacher(login, new StartedByTeacherTO(gameID, type.getPath()));

        return "ok";
    }

    /**
     * Returns which game has been started by teacher.
     * 
     * @param login
     * @return
     */
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    public StartedByTeacherTO getStartedByTeacher(@HeaderParam("login") String login)
    {
        String teachersLogin = studentManager.getMyTeachersLogin(login);
        StartedByTeacherTO startedByTeacher = GameHelper.getStartedByTeacher(teachersLogin);

        return startedByTeacher;
    }

    /**
     * Returns how many points scored student.
     * 
     * @param login
     * @return
     */
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/points")
    @Produces(MediaType.APPLICATION_JSON)
    public PointsTO getPoints(@HeaderParam("login") String login)
    {
        PointsTO pointsTO = gameResultManager.getPoints(login);

        return pointsTO;
    }

    /**
     * Reduce available points.
     * 
     * @param login
     * @param points - how many points student spent on shopping
     * @return
     */
    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/points")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String spendPoints(@HeaderParam("login") String login, int points)
    {
        gameResultManager.spendPoints(points, login);

        return "ok";
    }

}
