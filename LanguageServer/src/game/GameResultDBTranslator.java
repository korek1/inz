package game;

import dto.GameResult;
import utils.CommonUtils;

public class GameResultDBTranslator {

    private static final String VALUE_SEPARATOR = "#";
    private static final String RESUT_SEPARATOR = "%";
    
   
    public static String toDB(CurrentGame currGame)
    {
        int corectAnswers = currGame.getCorectAnswers();
        int noumberOfTasks = currGame.getNoumberOfTasks();
       
        long gameDuration = currGame.getGameDuration();
        int percentage = CommonUtils.getPercentage(corectAnswers, noumberOfTasks);
        
        String result = gameDuration + VALUE_SEPARATOR + percentage + RESUT_SEPARATOR;

        return result;
    }

    public static String fromDB(GameResult gameResult)
    {
        
        String resultsDBs = gameResult.getResult();

        String[] results = resultsDBs.split(RESUT_SEPARATOR);
        
        for (String result : results)
        {
            String[] split = result.split(VALUE_SEPARATOR);
            int duration = Integer.parseInt(split[0]);
            int pert = Integer.parseInt(split[1]);
        }
        
        return "jakies TO sie stworzy";
    }

}
