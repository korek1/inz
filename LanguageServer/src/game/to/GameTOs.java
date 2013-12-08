package game.to;

import java.util.ArrayList;
import java.util.List;

import utils.CommonUtils;

public class GameTOs {

    private List<GameTO> games = new ArrayList<>();

    public GameTOs()
    {
        super();
    }

    public List<GameTO> getGames()
    {
        return games;
    }

    public void setGames(List<GameTO> games)
    {
        this.games = games;
    }

    public void add(GameTO gameTO)
    {
        if (CommonUtils.isNull(games))
        {
            games = new ArrayList<>();
        }
        games.add(gameTO);
    }

}
