package game.impl;

import game.CurrentGame;

import java.util.List;

import dto.Game;

public class CurrentWordSearchGame extends CurrentGame {
    
    public static final long ESTIMATE_TIME_PER_TASK = 10000; //10 sek

    public CurrentWordSearchGame(Game game)
    {
        super(game);
    }

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;

        increaseAttempts();

        if (getSolution().remove(ids))
        {
            correct = true;
            increaseCorectAnswers();
        }

        return correct;
    }

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
