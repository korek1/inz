package game;

import game.to.SolutionTO;
import game.to.StartedByTeacherTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import spring.BeanHelper;
import spring.gameresult.GameResultManager;
import dto.GameResult;

public class GameHelper {

    static GameResultManager gameResultManager = (GameResultManager) BeanHelper.getBean("gameResultManagerImpl");

    private static Map<String, CurrentGame> currentStudentsGames = new HashMap<>();
    private static Map<String, StartedByTeacherTO> startedByTeacher = new HashMap<>();

    public static CurrentGame getCurrGame(String login)
    {
        return currentStudentsGames.get(login);
    }

    public static void startGameTeacher(String login, StartedByTeacherTO started)
    {
        startedByTeacher.put(login, started);
    }

    public static StartedByTeacherTO getStartedByTeacher(String login)
    {
        return startedByTeacher.get(login);
    }

    public static void startGame(String login, CurrentGame currentGame)
    {
        currentStudentsGames.put(login, currentGame);
    }

    public static boolean check(String login, int noumberOfTask, SolutionTO solution)
    {
        CurrentGame currentGame = currentStudentsGames.get(login);

        boolean checkIfPartOfGameIsCorrect = currentGame.checkIfPartOfGameIsCorrect(noumberOfTask - 1, solution.getDataFromStudent());

        if (currentGame.isGameFinished())
        {
            currentGame.setFinishTime(new Date());

            // calculate points and save to db
            PointsDirector pointsDirector = new PointsDirector(currentGame);

            GameResult produceGameResult = pointsDirector.produceGameResult();
            int gameAttempt = gameResultManager.saveOrUpdateGameResult(produceGameResult, login);

            int points = pointsDirector.calcPoints(gameAttempt);
            gameResultManager.addPoints(points, login);

            removeGame(login);
        }

        return checkIfPartOfGameIsCorrect;
    }

    private static void removeGame(String login)
    {
        currentStudentsGames.remove(login);
    }
}
