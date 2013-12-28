package game.to;

public class StartedByTeacherTO {

    private int gameID;
    private String gameType;

    public StartedByTeacherTO()
    {
        super();
    }

    public StartedByTeacherTO(int gameID, String gameType)
    {
        super();
        this.gameID = gameID;
        this.gameType = gameType;
    }

    public int getGameID()
    {
        return gameID;
    }

    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }

    public String getGameType()
    {
        return gameType;
    }

    public void setGameType(String gameType)
    {
        this.gameType = gameType;
    }

}
