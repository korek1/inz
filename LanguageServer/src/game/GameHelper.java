package game;

import game.to.SolutionTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dto.GameResult;
import spring.BeanHelper;
import spring.game.GameManager;
import spring.gameresult.GameResultManager;
import spring.gameresult.impl.GameResultManagerImpl;

public class GameHelper {
    
    static GameResultManager gameResultManager =  (GameResultManager) BeanHelper.getBean("gameResultManagerImpl"); 

    private static Map<String, CurrentGame> currentStudentsGames = new HashMap<>();

    public static void startGame(String login, CurrentGame currentGame)
    {
        currentStudentsGames.put(login, currentGame);
    }

    public static boolean check(String login, int noumberOfTask, SolutionTO solution)
    {
        CurrentGame currentGame = currentStudentsGames.get(login);
        
        boolean checkIfPartOfGameIsCorrect = currentGame.checkIfPartOfGameIsCorrect(noumberOfTask - 1, solution.getDataFromStudent());
        
        if(currentGame.isGameFinished())
        {
            currentGame.setFinishTime(new Date());
            
            //calculate points and save to db 
            PointsDirector pointsDirector = new PointsDirector(currentGame);
            GameResult produceGameResult = pointsDirector.produceGameResult();
            
            gameResultManager.saveOrUpdateGameResult(produceGameResult ,login);
            
            removeGame(login);
        }

        return checkIfPartOfGameIsCorrect;
    }
    
    private static void removeGame(String login)
    {
        currentStudentsGames.remove(login);
    }
}
