package dto.to;

import java.util.Date;

public class AvatarTO {

    private String firstName;
    private String lastName;
    private String login;
    private int orderNoumber;
    private int points;
    private Date lastAvatarUpdate;

    public AvatarTO()
    {
        super();
    }

    public AvatarTO(String firstName, String lastName, String login, int orderNoumber, int points, Date lastAvatarUpdate)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.orderNoumber = orderNoumber;
        this.points = points;
        this.lastAvatarUpdate = lastAvatarUpdate;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getOrderNoumber()
    {
        return orderNoumber;
    }

    public void setOrderNoumber(int orderNoumber)
    {
        this.orderNoumber = orderNoumber;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public Date getLastAvatarUpdate()
    {
        return lastAvatarUpdate;
    }

    public void setLastAvatarUpdate(Date lastAvatarUpdate)
    {
        this.lastAvatarUpdate = lastAvatarUpdate;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

}
