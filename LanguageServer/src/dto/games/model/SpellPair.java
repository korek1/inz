package dto.games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dto.Game;

@Entity
public class SpellPair {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    private String wordOk;
    private String wordWrong;
    private String polWord;

    public SpellPair()
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

    public String getWordOk()
    {
        return wordOk;
    }

    public void setWordOk(String wordOk)
    {
        this.wordOk = wordOk;
    }

    public String getWordWrong()
    {
        return wordWrong;
    }

    public void setWordWrong(String wordWrong)
    {
        this.wordWrong = wordWrong;
    }

    public String getPolWord()
    {
        return polWord;
    }

    public void setPolWord(String polWord)
    {
        this.polWord = polWord;
    }
    
    

}
