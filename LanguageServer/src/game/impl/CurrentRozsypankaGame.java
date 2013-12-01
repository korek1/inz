package game.impl;

import game.CurrentGame;

import java.util.List;
import java.util.Map;

public class CurrentRozsypankaGame extends CurrentGame {

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;
        List<Integer> list = getSolution().get(id);
        if (list.equals(ids))
        {
            correct = true;
        }
        return correct;
    }

    private List<Map<Integer, String>> processRozsypanka;

    public List<Map<Integer, String>> getProcessRozsypanka()
    {
        return processRozsypanka;
    }

    public void setProcessRozsypanka(List<Map<Integer, String>> processRozsypanka)
    {
        this.processRozsypanka = processRozsypanka;
    }

}
