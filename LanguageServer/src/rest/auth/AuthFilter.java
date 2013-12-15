package rest.auth;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import utils.Constants;
import auth.AuthMenager;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException
    {

        String role = validateUser(request);
        System.out.println(role);
        request.setSecurityContext(new SecurityContextImpl(role));

    }

    private String validateUser(ContainerRequestContext request)
    {
        String role = Role.STUDENT;

        boolean validStudent = false;
        boolean validTeacher = false;

        String login = request.getHeaderString(Constants.LOGIN);
        String pass = request.getHeaderString(Constants.PASSWORD);

        validStudent = AuthMenager.veryfiPassStudent(login, pass);
        
        if (!validStudent)
        {
            role = Role.TEACHER;
            validTeacher = AuthMenager.veryfiPassTeacher(login, pass);
            if (!validTeacher)
            {
                 request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                 .entity("UNAUTHORIZED")
                 .build());
            }
        }


        return role;

    }

}
