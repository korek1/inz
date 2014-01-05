package game.impl;

import game.CurrentGame;

import java.util.List;

import dto.Game;

public class CurrentWordSearchGame extends CurrentGame {

    public CurrentWordSearchGame(Game game)
    {
        super(game);
    }

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;

        boolean canPerformChecking = manageAttempt(id);
        if (canPerformChecking)
        {
            increaseAttempts();

            if (getSolution().contains(ids))
            {
                correct = true;
                increaseCorectAnswers();
            }
        }
        return correct;
    }
    
    public boolean isGameFinished()
    {
        return getCorectAnswers() == getNoumberOfTasks() ? true : false;
    }

}
