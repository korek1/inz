package dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Game {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int category;

    private int difficultyFactor;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "teacherId")
    private Teacher owner;

    public Game()
    {
        super();
    }

    public Game(String name)
    {
        super();
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Teacher getOwner()
    {
        return owner;
    }

    public void setOwner(Teacher owner)
    {
        this.owner = owner;
    }

    public int getCategory()
    {
        return category;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public int getDifficultyFactor()
    {
        return difficultyFactor;
    }

    public void setDifficultyFactor(int difficultyFactor)
    {
        this.difficultyFactor = difficultyFactor;
    }

}
