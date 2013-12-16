package rest;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;

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
import animal.AnimalDirHelper;

@Path("animal")
public class ZwierzRest {

    public ZwierzRest()
    {
        super();
    }

    @POST
    @RolesAllowed({ Role.STUDENT })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String storeAnimalData(@HeaderParam("login") String login, @FormDataParam("file") InputStream is)
    {
        AnimalDirHelper.storeAnimal(login, is);

        return "ok";
    }

    @GET
    @RolesAllowed({ Role.STUDENT })
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getAnimalData(@HeaderParam("login") String login) throws ParseException
    {

        FormDataMultiPart multiPart = new FormDataMultiPart();
        File file = AnimalDirHelper.getAnimalFile(login);

        multiPart.field("animal", (Object) file, new MediaType("file", "x"));

        return Response.ok(multiPart, MediaType.MULTIPART_FORM_DATA).build();
    }

}
