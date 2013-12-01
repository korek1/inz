package rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import utils.Constants;
import auth.AuthMenager;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException
    {
        validateUser(request);
    }

    private void validateUser(ContainerRequestContext request)
    {
        boolean validStudent = false;
        boolean validTeacher = false;

        
        String login = request.getHeaderString(Constants.LOGIN);
        String pass = request.getHeaderString(Constants.PASSWORD);

        validStudent = AuthMenager.veryfiPassStudent(login, pass);

        if (!validStudent)
        {
            validTeacher = AuthMenager.veryfiPassTeacher(login, pass);

            if (!validTeacher)
            {
//                 request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
//                         .entity("UNAUTHORIZED")
//                         .build());
            }
        }

    }

}
