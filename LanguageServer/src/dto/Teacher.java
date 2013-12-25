package dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dto.to.toserver.TeacherInsertTO;

@Entity
@Table(name = "TEACHERS")
public class Teacher {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String login;

    private String password;

    private Date joinDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<Klasa> klasy = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Game> games = new HashSet<>();

    public Teacher()
    {
        super();
    }

    public Teacher(String firstName, String lastName, String password, String login)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = new Date();
        this.password = password;
        this.login = login;
    }

    public Teacher(TeacherInsertTO teacherInsertTO)
    {
        super();
        this.firstName = teacherInsertTO.getFirstName();
        this.lastName = teacherInsertTO.getLastName();
        this.login = teacherInsertTO.getLogin();
        this.password = teacherInsertTO.getPassword();
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

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Set<Klasa> getKlasy()
    {
        return klasy;
    }

    public void setKlasy(Set<Klasa> klasy)
    {
        this.klasy = klasy;
    }

    public Set<Game> getGames()
    {
        return games;
    }

    public void setGames(Set<Game> games)
    {
        this.games = games;
    }

}
