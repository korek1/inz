package dto.to;

import java.util.List;

public class IsOnlineTO {

    private List<String> logins;

    private IsOnlineTO()
    {
        super();
    }

    public List<String> getLogins()
    {
        return logins;
    }

    public void setLogins(List<String> logins)
    {
        this.logins = logins;
    }

}
