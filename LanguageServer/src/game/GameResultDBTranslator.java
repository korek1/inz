package game;

import dto.Game;
import dto.GameResult;
import dto.to.gameresult.GameResultTO;
import dto.to.gameresult.ScoreTO;
import utils.CommonUtils;

public class GameResultDBTranslator {

    public static final String VALUE_SEPARATOR = "#";
    public static final String RESUT_SEPARATOR = "%";

    public static String toDB(CurrentGame currGame)
    {
        int corectAnswers = currGame.getCorectAnswers();
        int noumberOfTasks = currGame.getNoumberOfTasks();

        long gameDuration = currGame.getGameDuration();
        int percentage = CommonUtils.getPercentage(corectAnswers, noumberOfTasks);
        int attempts = currGame.getAttempts();

        String result = gameDuration + VALUE_SEPARATOR + percentage + VALUE_SEPARATOR + attempts + RESUT_SEPARATOR;

        return result;
    }

    public static GameResultTO fromDB(GameResult gameResult)
    {
        GameResultTO gameResultTO = new GameResultTO();

        String resultsDBs = gameResult.getResult();
        String[] results = resultsDBs.split(RESUT_SEPARATOR);

        for (String result : results)
        {
            String[] split = result.split(VALUE_SEPARATOR);
            int duration = Integer.parseInt(split[0]);
            int score = Integer.parseInt(split[1]);
            int attemps = Integer.parseInt(split[2]);

            ScoreTO scoreTO = new ScoreTO(duration, score, attemps);

            gameResultTO.addScoreTO(scoreTO);
        }

        Game game = gameResult.getGame();
        int gameID = game.getId();
        String name = game.getName();

        gameResultTO.setGameID(gameID);
        gameResultTO.setGameName(name);
        gameResultTO.setAttempts(gameResultTO.getScores().size());

        return gameResultTO;
    }

}
