package dto.to.gameresult;

import java.util.ArrayList;
import java.util.List;

public class GameResultClassTOs {

    List<GameResultClassTO> classResult = new ArrayList<>();

    public GameResultClassTOs()
    {
        super();
    }

    public void addGameResultClassTO(GameResultClassTO gameResultClassTO)
    {
        classResult.add(gameResultClassTO);
    }

}
