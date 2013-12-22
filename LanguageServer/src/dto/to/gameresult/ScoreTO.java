package dto.to.gameresult;

public class ScoreTO {

    private int duration;
    private int score;

    public ScoreTO()
    {
        super();
    }

    public ScoreTO(int duration, int score)
    {
        super();
        this.duration = duration;
        this.score = score;
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

}
