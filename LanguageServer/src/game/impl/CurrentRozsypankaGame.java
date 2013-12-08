package game.impl;

import game.CurrentGame;

import java.util.List;
import java.util.Map;

public class CurrentRozsypankaGame extends CurrentGame {
    

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
