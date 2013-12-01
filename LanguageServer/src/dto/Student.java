package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private Date joinDate;

    private Date lastLoginDate;

    private int orderNoumber;

    @ManyToOne
    @JoinColumn(name = "klasaId")
    private Klasa klasa;

    @OneToOne(cascade = { CascadeType.ALL })
    private Tamagotchi animal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<GameResult> gameHistory = new ArrayList<>();

    public Student()
    {
        super();
    }

    public Student(String firstName, String lastName, int orderNoumber, String password, String login)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderNoumber = orderNoumber;
        this.joinDate = new Date();
        this.password = password;
        this.login = login;
    }

    public void setKlasa(Klasa klasa)
    {
        this.klasa = klasa;
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

    public Date getJoinDate()
    {
        return joinDate;
    }

    public void setJoinDate(Date joinDate)
    {
        this.joinDate = joinDate;
    }

    public int getOrderNoumber()
    {
        return orderNoumber;
    }

    public void setOrderNoumber(int orderNoumber)
    {
        this.orderNoumber = orderNoumber;
    }

    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public Tamagotchi getAnimal()
    {
        return animal;
    }

    public void setAnimal(Tamagotchi animal)
    {
        this.animal = animal;
    }

    public Klasa getKlasa()
    {
        return klasa;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<GameResult> getGameHistory()
    {
        return gameHistory;
    }

    public void setGameHistory(List<GameResult> gameHistory)
    {
        this.gameHistory = gameHistory;
    }

    

}
