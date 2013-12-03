package game.to;

import java.util.Collections;
import java.util.List;

import dto.Game;
import dto.games.RozsypankaGame;

public class TOsManager {

    public static RozsypankaGameTO processRozsypanka(RozsypankaGame rozsypankaGame, boolean setSentences)
    {
        RozsypankaGameTO rozsypankaGameTO = new RozsypankaGameTO();
        rozsypankaGameTO.setId(rozsypankaGame.getId());
        rozsypankaGameTO.setName(rozsypankaGame.getName());
        if (setSentences)
        {
            rozsypankaGameTO.setSentences(rozsypankaGame.getSentences());
        }
        return rozsypankaGameTO;

    }

    public static RozsypankaGameTO processRozsypanka(RozsypankaGame rozsypankaGame)
    {

        return processRozsypanka(rozsypankaGame, true);

    }

    public static RozsypankaGameTOs processRozsypankas(List<Game> rozsypankaGames)
    {
        RozsypankaGameTOs rozsypankaGamesTO = new RozsypankaGameTOs();

        for (Game rozsypankaGame : rozsypankaGames)
        {
            RozsypankaGameTO processRozsypanka = processRozsypanka((RozsypankaGame) rozsypankaGame, false);
            rozsypankaGamesTO.addRozsypankaTO(processRozsypanka);

        }

        return rozsypankaGamesTO;
    }
    
    public static RozsypankaGameStudentTO processRozsypankaForStudent(List<List<MappedWordTO>> processRozsypanka)
    {
        RozsypankaGameStudentTO rozsypankaGameTO = null;

        for (List<MappedWordTO> list : processRozsypanka)
        {
            Collections.shuffle(list);
        }
        
        rozsypankaGameTO = new RozsypankaGameStudentTO(processRozsypanka);
        
        return rozsypankaGameTO;

    }

}
