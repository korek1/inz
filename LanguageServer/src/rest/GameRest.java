package rest;

import hibernatee.DBUtils;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import spring.BeanHelper;
import spring.game.GameManager;
import spring.klasa.KlasaManager;
import spring.teacher.TeacherManager;
import dto.Game;
import dto.RozsypankaGame;
import dto.Teacher;

@Path("game")
public class GameRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    TeacherManager teacherManager = (TeacherManager) BeanHelper.getBean("teacherManagerImpl");

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllKlase(RozsypankaGame rozsypankaGame, @HeaderParam("login") String login)
    {

        gameManager.insertGame(rozsypankaGame, login);

        return "succes";
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getGames(@HeaderParam("login") String login)
    {

        Set<Game> allGames = teacherManager.getAllGames(login);
        DBUtils.cleanGames(allGames);
        // temp solution
        Teacher teacher = new Teacher();

        teacher.setGames(allGames);

        return teacher;

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGame getGame(@PathParam("id") int id)
    {

        // tez temp :(
        Game game = gameManager.getGameById(id, RozsypankaGame.class);
        Set<Game> x = new HashSet<>();
        x.add(game);
        DBUtils.cleanGames(x);

        return (RozsypankaGame) game;

    }

}
