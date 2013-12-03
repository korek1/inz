package game;

import game.data.GameDataParser;
import game.impl.CurrentRozsypankaGame;
import game.to.MappedWordTO;
import game.to.RozsypankaGameStudentTO;
import game.to.TOsManager;

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

    public static RozsypankaGameStudentTO createAndStartCurrRozsypanka(RozsypankaGame rozsypankaGame, String login)
    {
        CurrentRozsypankaGame currRozsypanka = new CurrentRozsypankaGame();

        setStartDate(currRozsypanka);
        List<String> sentences = rozsypankaGame.getSentences();

        List<List<MappedWordTO>> processRozsypanka = GameDataParser.processRozsypanka(sentences);

        List<List<Integer>> solutionToRemember = new ArrayList<>();

        for (List<MappedWordTO> list : processRozsypanka)
        {
            List<Integer> temp = new ArrayList<>();
            for (MappedWordTO mappedWordTO : list)
            {
                temp.add(mappedWordTO.getMappedValue());
            }
            solutionToRemember.add(temp);
        }

        currRozsypanka.setSolution(solutionToRemember);

        GameHelper.startGame(login, currRozsypanka);

        RozsypankaGameStudentTO rozsypankaGameStudentTO = TOsManager.processRozsypankaForStudent(processRozsypanka);

        return rozsypankaGameStudentTO;
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
