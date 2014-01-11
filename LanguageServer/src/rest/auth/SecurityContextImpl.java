package rest.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class SecurityContextImpl implements SecurityContext {

    private final String role;

    public SecurityContextImpl(final String role)
    {
        super();
        this.role = role;
    }

    @Override
    public String getAuthenticationScheme()
    {
        return null;
    }

    @Override
    public Principal getUserPrincipal()
    {
        return null;
    }

    @Override
    public boolean isSecure()
    {
        return false;
    }

    @Override
    public boolean isUserInRole(String role)
    {

        if (this.role.equalsIgnoreCase(role))
        {
            return true;
        }

        return false;
    }

}
