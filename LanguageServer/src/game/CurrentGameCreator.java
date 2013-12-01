package game;

import game.data.GameDataParser;
import game.impl.CurrentRozsypankaGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.mapping.Collection;

import dto.games.RozsypankaGame;

public class CurrentGameCreator {

    public static void createCurrMemo()
    {

    }

    public static CurrentRozsypankaGame createCurrRozsypanka(RozsypankaGame rozsypankaGame)
    {
        CurrentRozsypankaGame currRozsypanka = new CurrentRozsypankaGame();

        setStartDate(currRozsypanka);
        List<String> sentences = rozsypankaGame.getSentences();

        List<Map<Integer, String>> processRozsypanka = GameDataParser.processRozsypanka(sentences);

        List<List<Integer>> solutionToRemember = new ArrayList<>();

        for (Map<Integer, String> map : processRozsypanka)
        {
            List<Integer> keys = new ArrayList<Integer>(map.keySet());
            solutionToRemember.add(keys);
        }

        currRozsypanka.setSolution(solutionToRemember);
        currRozsypanka.setProcessRozsypanka(processRozsypanka);

        return currRozsypanka;
    }

    public static void createCurrMillionaire()
    {

    }

    public static void createCurrWordSearch()
    {

    }

    private static void setStartDate(CurrentGame currGame)
    {
        currGame.setStartTime(new Date());
    }

}
