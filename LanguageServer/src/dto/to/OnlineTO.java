package dto.to;

public class OnlineTO {

    private String login;
    private boolean online;

    public OnlineTO()
    {
        super();
    }

    public OnlineTO(String login, boolean online)
    {
        super();
        this.login = login;
        this.online = online;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public boolean isOnline()
    {
        return online;
    }

    public void setOnline(boolean online)
    {
        this.online = online;
    }

}
