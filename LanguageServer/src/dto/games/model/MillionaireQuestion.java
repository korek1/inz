package dto.games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dto.Game;

@Entity
public class MillionaireQuestion {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctAnswer;

    public MillionaireQuestion()
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

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer1()
    {
        return answer1;
    }

    public void setAnswer1(String answer1)
    {
        this.answer1 = answer1;
    }

    public String getAnswer2()
    {
        return answer2;
    }

    public void setAnswer2(String answer2)
    {
        this.answer2 = answer2;
    }

    public String getAnswer3()
    {
        return answer3;
    }

    public void setAnswer3(String answer3)
    {
        this.answer3 = answer3;
    }

    public String getAnswer4()
    {
        return answer4;
    }

    public void setAnswer4(String answer4)
    {
        this.answer4 = answer4;
    }

    public int getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

}
