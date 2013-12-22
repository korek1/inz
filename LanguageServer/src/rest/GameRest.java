package rest;

import game.GameHelper;
import game.to.SolutionTO;
import game.to.TOsGameManager;

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
import spring.gameresult.GameResultManager;
import dto.to.GameCategoryTOs;
import dto.to.gameresult.GameResultTOs;

@Path("game")
public class GameRest {
    
    GameResultManager gameResultManager = (GameResultManager) BeanHelper.getBean("gameResultManagerImpl");

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
    
    
    /**
     * Returns student's game history
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

}
