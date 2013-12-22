package dto.to.gameresult;

import java.util.ArrayList;
import java.util.List;

public class GameResultTO {

    private int gameID;
    private String gameName;
    private int attempts;
    private List<ScoreTO> scores = new ArrayList<>();

    public GameResultTO()
    {
        super();
    }

    public int getGameID()
    {
        return gameID;
    }

    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }

    public int getAttempts()
    {
        return attempts;
    }

    public void setAttempts(int attempts)
    {
        this.attempts = attempts;
    }

    public List<ScoreTO> getScores()
    {
        return scores;
    }

    public void setScores(List<ScoreTO> scores)
    {
        this.scores = scores;
    }

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    public void addScoreTO(ScoreTO scoreTO)
    {
        scores.add(scoreTO);
    }

}
