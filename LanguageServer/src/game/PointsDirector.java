package game;

import dto.GameResult;

/**
 * Has logic to calculate points from finished curr game and produce GameResult
 * 
 * @author acer
 * 
 */
public class PointsDirector {

    private CurrentGame currGame;
    private int points;

    private final double TIME_FACTOR = 1.00;
    private final double DIFF_FACTOR = 0.1;

    private final int TOTAL_POINTS_FOR_SINGLE_GAME = 100; 

    public PointsDirector(CurrentGame currGame)
    {
        super();
        this.currGame = currGame;
    }

    public GameResult produceGameResult()
    {
        GameResult gameResult = new GameResult();
        gameResult.setGame(currGame.getGame());

        points = calculatePoints();
        
        String string = GameResultDBTranslator.toDB(currGame);
        gameResult.setResult(string);

        System.out.println("punkty : " + points );
        
        return gameResult;

    }

    private int calculatePoints()
    {
        int pointsForGame = 0; 

        long gameDuration = currGame.getGameDuration();

        int corectAnswers = currGame.getCorectAnswers();

        int noumberOfTasks = currGame.getNoumberOfTasks();

        int difficultyFactor = currGame.getGame().getDifficultyFactor();

        double gameDurationPoints = calcGameDurationPoints(gameDuration, noumberOfTasks);

        double calcCorectPoints = calcCorectPoints(corectAnswers, noumberOfTasks);

        pointsForGame = (int) ((calcCorectPoints - gameDurationPoints) * ((difficultyFactor * DIFF_FACTOR)));

        return pointsForGame > 0 ? pointsForGame : 0;
    }

    private double calcGameDurationPoints(long gameDuration, int noumberOfTasks)
    {
        return ((gameDuration / noumberOfTasks) * TIME_FACTOR);
    }

    private double calcCorectPoints(int corectAnswers, int noumberOfTasks)
    {

        return ((corectAnswers / noumberOfTasks) * TOTAL_POINTS_FOR_SINGLE_GAME);
    }

    public int getPoints()
    {
        return points;
    }
    
    
}
