package rest;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import rest.auth.Role;
import spring.BeanHelper;
import spring.student.StudentManager;
import utils.CommonUtils;
import animal.AnimalDirHelper;
import dto.to.AvatarTOs;
import dto.to.LoginsTO;

@Path("animal")
public class ZwierzRest {

    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");

    public ZwierzRest()
    {
        super();
    }

    /**
     * Saves student's files on server. Only files which has been modified should be sent to server.
     * 
     * @param login
     * @param is
     * @param eq
     * @param info
     * @param img
     * @return
     */
    @POST
    @RolesAllowed({ Role.STUDENT })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String storeAnimalData(@HeaderParam("login") String login, @FormDataParam("file") InputStream is, @FormDataParam("eq") InputStream eq, @FormDataParam("info") InputStream info, @FormDataParam("img") InputStream img)
    {
        AnimalDirHelper.storeAnimal(login, is, eq, info, img);

        if (CommonUtils.isNotNull(img))
        {
            studentManager.updateLastAvatarUpdate(login);
        }

        return "ok";
    }

    /**
     * Returns student's files
     * 
     * @param login
     * @return
     * @throws ParseException
     */
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getAnimalData(@HeaderParam("login") String login) throws ParseException
    {

        FormDataMultiPart multiPart = new FormDataMultiPart();
        List<File> animalFile = AnimalDirHelper.getAnimalFile(login);

        multiPart.field("file", (Object) animalFile.get(0), new MediaType("file", "x"));
        multiPart.field("eq", (Object) animalFile.get(1), new MediaType("file", "x"));
        multiPart.field("info", (Object) animalFile.get(2), new MediaType("file", "x"));
        multiPart.field("img", (Object) animalFile.get(3), new MediaType("file", "x"));

        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

    /**
     * Returns info about class scores (points) and last time avatar update of each student in a class
     * 
     * @param login
     * @return
     * @throws ParseException
     */
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/avatar")
    @Produces(MediaType.APPLICATION_JSON)
    public AvatarTOs getAvatarsInfo(@HeaderParam("login") String login) throws ParseException
    {
        AvatarTOs avatarTOs = studentManager.getAvatarsTO(login);

        return avatarTOs;
    }

    /**
     * Returns avatar (image).
     * 
     * @param logins - list of student's logins. For each login method returns his avatar (students are identified by logins)
     * @return
     * @throws ParseException
     */
    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/avatar")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAvatars(LoginsTO logins) throws ParseException
    {

        FormDataMultiPart multiPart = new FormDataMultiPart();

        for (String login : logins.getLogins())
        {
            File animalAvatar = AnimalDirHelper.getStudentsAnimalAvatar(login);
            if (CommonUtils.isNotNull(animalAvatar))
            {
                multiPart.field(login, (Object) animalAvatar, new MediaType("file", "x"));
            }
        }

        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

}
