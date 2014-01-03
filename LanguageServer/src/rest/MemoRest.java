package rest;

import game.CurrentGameCreator;
import game.helpers.MemoDirHelper;
import game.to.GameTO;
import game.to.GameTOs;
import game.to.TOsGameManager;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.google.gson.Gson;

import rest.auth.Role;
import spring.BeanHelper;
import spring.game.GameManager;
import spring.student.StudentManager;
import utils.FileUtils;
import dto.Game;
import dto.games.MemoGame;
import dto.games.model.PicWordPair;

@Path("game")
public class MemoRest {

    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl");
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public MemoRest()
    {
        super();
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/memo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertMemo(@HeaderParam("login") String login, @HeaderParam("name") String gameName, FormDataMultiPart multiPart)
    {
        List<FormDataBodyPart> info = multiPart.getFields("gamedetails");

        FormDataBodyPart bodyPartInfo = info.get(0);
        String string = bodyPartInfo.getValueAs(String.class);

        Gson gson = new Gson();
        GameTO gameTO = gson.fromJson(string, GameTO.class);

        MemoGame memoGame = TOsGameManager.memoConvert(gameTO);

        gameManager.insertGame(memoGame, login);
        int gameID = memoGame.getId();

        List<PicWordPair> picWordPairList = new ArrayList<>();

        Map<String, List<FormDataBodyPart>> fields = multiPart.getFields();

        for (Map.Entry<String, List<FormDataBodyPart>> entry : fields.entrySet())
        {
            String word = entry.getKey();
            if (!word.equals("gamedetails"))
            {
                PicWordPair picWordPair = new PicWordPair();
                picWordPair.setWord(word);
                picWordPair.setGame(memoGame);

                List<FormDataBodyPart> value = entry.getValue();
                for (FormDataBodyPart formDataBodyPart : value)
                {
                    InputStream stream = formDataBodyPart.getValueAs(InputStream.class);
                    ContentDisposition contentDisposition = formDataBodyPart.getContentDisposition();
                    String fileName = contentDisposition.getFileName();
                    String extension = FileUtils.getExtension(fileName);
                    String location = MemoDirHelper.saveMemoPic(login, gameID, stream, extension);
                    picWordPair.setPicPath(location);
                }

                picWordPairList.add(picWordPair);
            }
        }
        memoGame.setPicWordPair(picWordPairList);
        gameManager.update(memoGame);

        return "hehs";

    }

    @GET
    @Path("/memos")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getMemos(@HeaderParam("login") String login) throws ParseException
    {
        List<Game> allGames = gameManager.getAllGames(login, MemoGame.class);

        GameTOs gameTOs = TOsGameManager.processGames(allGames);

        return gameTOs;

    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/memo/{id}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getMemo(@HeaderParam("login") String login, @PathParam("id") int gameID) throws ParseException
    {
        MemoGame memoGame = gameManager.getMemoByID(gameID);
        List<PicWordPair> picWordPairList = memoGame.getPicWordPair();

        FormDataMultiPart multiPart = new FormDataMultiPart();
        for (PicWordPair picWordPair : picWordPairList)
        {
            String word = picWordPair.getWord();
            String picPath = picWordPair.getPicPath();
            String extension = FileUtils.getExtension(picPath);
            File pic = new File(picPath);

            multiPart.field(word, (Object) pic, new MediaType("image", extension));

        }

        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/memos")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getMemosStudent(@HeaderParam("login") String login) throws ParseException
    {
        String teacherLogin = studentManager.getMyTeachersLogin(login);

        return getMemos(teacherLogin);

    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/memo/{id}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getMemoStudent(@HeaderParam("login") String login, @PathParam("id") int gameID) throws ParseException
    {
        MemoGame memoGame = gameManager.getMemoByID(gameID);

        FormDataMultiPart multiPart = CurrentGameCreator.createAndStartCurrMemo(memoGame, login);

        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

}
