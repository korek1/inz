package dto.to.toserver;

public class StudentInsertTO {

    private String firstName;

    private String lastName;

    private String password;

    private int orderNoumber;

    private int idKlasy;
    
    private boolean female;

    public StudentInsertTO()
    {
        super();
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

    public int getIdKlasy()
    {
        return idKlasy;
    }

    public void setIdKlasy(int idKlasy)
    {
        this.idKlasy = idKlasy;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isFemale()
    {
        return female;
    }

    public void setFemale(boolean female)
    {
        this.female = female;
    }
    

}
