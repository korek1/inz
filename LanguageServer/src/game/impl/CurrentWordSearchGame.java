package game.impl;

import game.CurrentGame;

import java.util.List;

public class CurrentWordSearchGame extends CurrentGame {

    public CurrentWordSearchGame()
    {
        super();
    }

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;
        if (getSolution().contains(ids))
        {
            correct = true;
        }
        return correct;
    }

}
