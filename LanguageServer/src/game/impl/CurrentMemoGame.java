package game.impl;

import java.util.List;

import dto.Game;
import game.CurrentGame;

public class CurrentMemoGame extends CurrentGame {
    
    public static final long ESTIMATE_TIME_PER_TASK = 20;

    public CurrentMemoGame(Game game)
    {
        super(game);
    }

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean isCorrect = super.checkIfPartOfGameIsCorrect(id, ids);

        if (isCorrect == false)
        {
            removeFromAttempted(id);
        }

        return isCorrect;
    }

    @Override
    public boolean isGameFinished()
    {
        return getCorectAnswers() == getNoumberOfTasks() ? true : false;
    }

    @Override
    public long getEstimatedTimetoFinishGame()
    {
        return ESTIMATE_TIME_PER_TASK * getNoumberOfTasks();
    }

}
