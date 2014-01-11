package dto.to;

import java.util.ArrayList;
import java.util.List;

public class LoginsTO {

    private List<String> logins = new ArrayList<>();

    public LoginsTO()
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
