package rest;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

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

        String login = request.getHeaderString("login");
        String pass = request.getHeaderString("pass");

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
