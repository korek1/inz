package game.impl;

import dto.Game;
import game.CurrentGame;

public class CurrentSpellGame extends CurrentGame {
    
    public static final long ESTIMATE_TIME_PER_TASK = 20000; //20 sek

    public CurrentSpellGame(Game game)
    {
        super(game);
    }

    @Override
    public long getEstimatedTimetoFinishGame()
    {
        return ESTIMATE_TIME_PER_TASK * getNoumberOfTasks();
    }
}
