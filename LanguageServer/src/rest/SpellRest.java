package rest;

import game.CurrentGameCreator;
import game.GameRest;
import game.to.GameTOs;
import game.to.TOsGameManager;
import game.to.spell.SpellGameStudentTO;
import game.to.spell.SpellGameTO;

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
import dto.games.SpellGame;

@Path("game")
public class SpellRest implements GameRest<SpellGameTO, SpellGameStudentTO> {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/spell")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer insertGame(SpellGameTO spellGameTO, @HeaderParam("login") String login)
    {
        SpellGame spellGame = TOsGameManager.covertSpellGameTO(spellGameTO);

        Integer id = gameManager.insertGame(spellGame, login);

        return id;
    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/spell/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SpellGameTO getGame(@PathParam("id") int id)
    {

        SpellGame spellGame = gameManager.getSpellGameByID(id);
        SpellGameTO spellGameTO = TOsGameManager.convertSpellGame(spellGame);

        return spellGameTO;

    }
    
    @DELETE
    @RolesAllowed({ Role.TEACHER })
    @Path("/spell/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteGame(@PathParam("id") int id)
    {

        gameManager.delete(id);

        return "ok";

    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/spells")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, SpellGame.class);
        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/spells")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getAllGamesForStudent(@HeaderParam("login") String login)
    {

        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getAllGames(teacherLogin);
    }

    @GET
    @Path("/student/spell/{id}")
    @RolesAllowed({ Role.STUDENT })
    @Produces(MediaType.APPLICATION_JSON)
    public SpellGameStudentTO getGameForStudent(@HeaderParam("login") String login, @PathParam("id") int id)
    {

        SpellGame spellGame = gameManager.getSpellGameByID(id);

        SpellGameStudentTO spellGameStudentTO = CurrentGameCreator.createAndStartCurrSpellGame(spellGame, login);

        return spellGameStudentTO;

    }

}
