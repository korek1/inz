package login;

import hibernatee.DBController;

import java.util.Random;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import auth.Auth;
import auth.AuthMenager;

@Path("/")
public class LoginRest {

    private DBController db = DBController.getInstance();

    @POST
    @Path("student")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String logInStudent(Auth auth)
    {
        String tempPass = "";
        boolean valid = db.validateStudent(auth);

        if (valid)
        {
            int nextInt = new Random().nextInt();

            tempPass = auth.getLogin() + Integer.toString(nextInt);

            AuthMenager.addTempPassStudent(auth.getLogin(), tempPass);
        }

        return tempPass;
    }

    @POST
    @Path("teacher")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String logInTeacher(Auth auth)
    {

        String tempPass = "";
        boolean valid = db.validateTeacher(auth);

        if (valid)
        {
            int nextInt = new Random().nextInt();

            tempPass = auth.getLogin() + Integer.toString(nextInt);

            AuthMenager.addTempPassTeacher(auth.getLogin(), tempPass);
        }

        return tempPass;
    }

    @GET
    @Path("online")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStudent()
    {
        Set<String> onlineAll = AuthMenager.onlineAll();

        return onlineAll.toString();
    }

}
