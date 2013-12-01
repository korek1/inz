package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameHelper {

    private static Map<String, CurrentGame> currentStudentsGames = new HashMap<>();

    public static void startGame(String login, CurrentGame currentGame)
    {
        currentStudentsGames.put(login, currentGame);
    }

    public static boolean check(String login, int noumberOfTask, List<Integer> dataInThisTask)
    {
        CurrentGame currentGame = currentStudentsGames.get(login);
        boolean checkIfPartOfGameIsCorrect = currentGame.checkIfPartOfGameIsCorrect(noumberOfTask, dataInThisTask);

        return checkIfPartOfGameIsCorrect;
    }
}
