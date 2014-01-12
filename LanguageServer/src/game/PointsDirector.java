package game;

import utils.CommonUtils;
import dto.GameResult;

/**
 * Has logic to calculate points from finished curr game and produce GameResult
 * 
 * @author acer
 * 
 */
public class PointsDirector {

    private CurrentGame currGame;

    private final int BASE_FOR_GAME = 100;

    private final int BASE_TIME_BONUS = 100;

    public PointsDirector(CurrentGame currGame)
    {
        super();
        this.currGame = currGame;
    }

    public GameResult produceGameResult()
    {
        GameResult gameResult = new GameResult();
        gameResult.setGame(currGame.getGame());

        String string = GameResultDBTranslator.toDB(currGame);
        gameResult.setResult(string);

        return gameResult;

    }

    public int calcPoints()
    {
        int points = ( ( pointsFormDiff() + timePoints() ) * correctness() ) / 100;
        
        return points > 0 ? points : 0;
    }

    /**
     * Max 150. Min -50;
     * @return
     */
    private int timePoints()
    {
        int points = 0;
        
        long estimatedTimetoFinishGame = currGame.getEstimatedTimetoFinishGame();
        long gameDuration = currGame.getGameDuration();

        if(estimatedTimetoFinishGame >= 1.5f * gameDuration) // 1.5 times quicker
        {
            points = (int) (BASE_TIME_BONUS * 1.5f);
        }
        else if(estimatedTimetoFinishGame >= gameDuration)
        {
            points = BASE_TIME_BONUS;
        }
        else if(gameDuration > 2 * estimatedTimetoFinishGame) // over 2 times longer
        {
            points = -50;
        }
        
        return points;
    }

    /**
     * % in int
     * 
     * @return
     */
    private int correctness()
    {
        int corectAnswers = currGame.getCorectAnswers();
        int noumberOfTasks = currGame.getNoumberOfTasks();

        return CommonUtils.getPercentage(corectAnswers, noumberOfTasks);
    }

    /**
     * Max 200 points. Min 110 points.
     * 
     * @return
     */
    private int pointsFormDiff()
    {
        int difficultyFactor = currGame.getGame().getDifficultyFactor();

        return BASE_FOR_GAME + difficultyFactor * 10;
    }

}
