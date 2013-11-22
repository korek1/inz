package dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GameResult {
    
    @Id @GeneratedValue
    private int id;
    @OneToOne(cascade=CascadeType.ALL)
    private Game game;
    private int score;
    private int time;
    
    
    
    public GameResult()
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
    public Game getGame()
    {
        return game;
    }
    public void setGame(Game game)
    {
        this.game = game;
    }
    public int getScore()
    {
        return score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    } 
    
    

}
