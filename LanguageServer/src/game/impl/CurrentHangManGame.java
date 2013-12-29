package game.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.CommonUtils;
import dto.Game;
import game.CurrentGame;

public class CurrentHangManGame extends CurrentGame {

    private List<Integer> letterPosition = new ArrayList<>();

    private Map<Integer, Integer> noumberOfCorrectLetter = new HashMap<>();
    private Map<Integer, Integer> noumberOfWrongAnswers = new HashMap<>();

    public CurrentHangManGame(Game game)
    {
        super(game);
    }

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;

        List<Integer> list = getSolution().get(id);
        Integer fromStudent = ids.get(0);

        Integer wrong = null;
        Integer integer = noumberOfCorrectLetter.get(id);
        if (CommonUtils.isNull(integer))
        {
            integer = new Integer(0);
            noumberOfCorrectLetter.put(id, integer);
        }

        letterPosition.clear();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals(fromStudent))
            {
                letterPosition.add(i);
                integer++;
            }
        }

        if (!letterPosition.isEmpty())
        {
            if (integer.equals(list.size()))
            {
                increaseAttempts();
                increaseCorectAnswers();
            }
            noumberOfCorrectLetter.put(id, integer);

            correct = true;
        }
        else
        {
            wrong = noumberOfWrongAnswers.get(id);
            if (CommonUtils.isNull(wrong))
            {
                wrong = new Integer(0);
            }
            wrong++;
            noumberOfWrongAnswers.put(id, wrong);

            if (wrong.equals(5))
            {
                increaseAttempts();
            }
        }

        return correct;
    }

    public List<Integer> getLetterPosition()
    {
        return letterPosition;
    }

}
