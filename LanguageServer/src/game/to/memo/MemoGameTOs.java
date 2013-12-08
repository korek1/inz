package game.to.memo;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class MemoGameTOs {

    private List<GameTO> games = new ArrayList<>();

    public MemoGameTOs()
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

}
