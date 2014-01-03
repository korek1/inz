package dto.to;

public class StudentTO {

    private int id;

    private String login;

    private String firstName;

    private String lastName;

    private int orderNoumber;

    private String klasa;

    private int totalPoints;

    public StudentTO()
    {
        super();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getKlasa()
    {
        return klasa;
    }

    public void setKlasa(String klasa)
    {
        this.klasa = klasa;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public int getTotalPoints()
    {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints)
    {
        this.totalPoints = totalPoints;
    }

}
