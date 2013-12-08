package rest;

import game.CurrentGameCreator;
import game.helpers.MemoDirHelper;
import game.to.GameTOs;
import game.to.TOsManager;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import spring.BeanHelper;
import spring.game.GameManager;
import spring.student.StudentManager;
import utils.FileUtils;
import dto.Game;
import dto.games.MemoGame;
import dto.games.model.PicWordPair;

@Path("game")
public class MemoRest extends Application{
    
    GameManager gameManager = (GameManager) BeanHelper.getBean("gameManagerImpl"); 
    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");
    
    public MemoRest()
    {
        super();
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MultiPartFeature.class);
        return classes;
    }
    
    @POST
    @Path("/memo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertMemo(@HeaderParam("login") String login, @HeaderParam("name") String gameName, FormDataMultiPart multiPart)
    {
        
        MemoGame memoGame = new MemoGame();
        memoGame.setName(gameName);
        gameManager.insertGame(memoGame, login);
        int gameID = memoGame.getId();
        List<PicWordPair> picWordPairList = new ArrayList<>();

        Map<String, List<FormDataBodyPart>> fields = multiPart.getFields();

        for (Map.Entry<String, List<FormDataBodyPart>> entry : fields.entrySet())
        {
            PicWordPair picWordPair = new PicWordPair();
            String word = entry.getKey();
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
        
        GameTOs gameTOs = TOsManager.processGames(allGames);
        
        return gameTOs;
        
    }

    @GET
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
            
            multiPart.field(word,(Object) pic, new MediaType("image", extension));
            
        }
        
        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }
    
    @GET
    @Path("/student/memos")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTOs getMemosStudent(@HeaderParam("login") String login) throws ParseException
    {
        String teacherLogin = studentManager.getMyTeachersLogin(login);
        
        return getMemos(teacherLogin);
        
    }
    
    @GET
    @Path("/student/memo/{id}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getMemoStudent(@HeaderParam("login") String login, @PathParam("id") int gameID) throws ParseException
    {
        MemoGame memoGame = gameManager.getMemoByID(gameID);
        
        FormDataMultiPart multiPart = CurrentGameCreator.createAndStartCurrMemo(memoGame, login);
        
        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

}
