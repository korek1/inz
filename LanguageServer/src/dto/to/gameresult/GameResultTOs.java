package dto.to.gameresult;

import java.util.ArrayList;
import java.util.List;

public class GameResultTOs {
    
    private List<GameResultTO> gameResults = new ArrayList<>();

    public GameResultTOs()
    {
        super();
    }

    public List<GameResultTO> getGameResults()
    {
        return gameResults;
    }

    public void setGameResults(List<GameResultTO> gameResults)
    {
        this.gameResults = gameResults;
    }
    
    public void addGameResultTO(GameResultTO gameResultTO)
    {
        gameResults.add(gameResultTO);
    }
    
    
    

}
