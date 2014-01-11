package game.impl;

import java.util.List;

import dto.Game;
import game.CurrentGame;

public class CurrentMemoGame extends CurrentGame {

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

}
