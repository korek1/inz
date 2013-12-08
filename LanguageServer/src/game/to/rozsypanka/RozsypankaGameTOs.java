package game.to.rozsypanka;

import java.util.ArrayList;
import java.util.List;

public class RozsypankaGameTOs {

    private List<RozsypankaGameTO> games = new ArrayList<>();

    public RozsypankaGameTOs()
    {
        super();
    }

    public List<RozsypankaGameTO> getGames()
    {
        return games;
    }

    public void setGames(List<RozsypankaGameTO> games)
    {
        this.games = games;
    }

    public void addRozsypankaTO(RozsypankaGameTO game)
    {
        games.add(game);
    }

}
