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
import dto.to.GameCategoryTOs;

@Path("game")
public class GameRest {

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

}
