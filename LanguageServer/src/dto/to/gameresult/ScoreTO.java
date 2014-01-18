package dto.to.gameresult;

public class ScoreTO {

    private int duration;
    private int score;
    private int attemps;

    public ScoreTO()
    {
        super();
    }

    public ScoreTO(int duration, int score, int attemps)
    {
        super();
        this.duration = duration;
        this.score = score;
        this.attemps = attemps;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getAttemps()
    {
        return attemps;
    }

    public void setAttemps(int attemps)
    {
        this.attemps = attemps;
    }
    
    

}
