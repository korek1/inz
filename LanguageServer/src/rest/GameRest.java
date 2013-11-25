package rest;

import hibernatee.DBUtils;

import java.util.HashSet;
import java.util.List;
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
import spring.student.StudentManager;
import spring.teacher.TeacherManager;
import utils.CommonUtils;
import dto.Game;
import dto.RozsypankaGame;
import dto.Teacher;

@Path("game")
public class GameRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    @POST
    @Path("/rozsypanka")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllKlase(RozsypankaGame rozsypankaGame, @HeaderParam("login") String login)
    {

        gameManager.insertGame(rozsypankaGame, login);

        return "succes";
    }

    @GET
    @Path("/rozsypanka/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RozsypankaGame getRozsypankaGame(@PathParam("id") int id)
    {

        RozsypankaGame game = gameManager.getRozsypankaById(id);
        DBUtils.cleanGame(game);

        return game;

    }

    //trzeba ustalic jakas sensowne linki dla studenta
    @GET
    @Path("/rozsypankas")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getRozsypankaGames(@HeaderParam("login") String login)
    {

        List<Game> allGames = gameManager.getAllGames(login, RozsypankaGame.class);
        DBUtils.cleanGames(allGames);

        // samego setu,listy nie chce JSONOWAC a jak collection jest w innym obiekiecie to daje rade (?)
        Teacher teacher = new Teacher();
        teacher.setGames(CommonUtils.ListToSet(allGames));

        return teacher;

    }
    
    /*dla studenta*/
    @GET
    @Path("/rozsypankass")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getGamesStudent(@HeaderParam("login") String login)
    {
        
        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getRozsypankaGames(teacherLogin);

    }
    

}
