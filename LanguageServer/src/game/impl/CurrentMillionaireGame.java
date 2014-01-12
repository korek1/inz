package game.impl;

import utils.CommonUtils;
import dto.Game;
import game.CurrentGame;
import game.helpers.Lifelines;

public class CurrentMillionaireGame extends CurrentGame {
    
    public static final long ESTIMATE_TIME_PER_TASK = 20000; //20 sek

    private Lifelines lifelines;

    public CurrentMillionaireGame(Game game)
    {
        super(game);
    }

    public synchronized String getLifeline(int type, int questionNoumber)
    {
        String lifeline = null;

        if (CommonUtils.isNull(lifelines))
        {
            lifelines = new Lifelines(getSolution());
        }

        if (type == 1)
        {
            lifeline = lifelines.produceFiftyFifty(questionNoumber);
        }
        else if (type == 2)
        {
            lifeline = lifelines.produceAskFriend(questionNoumber);
        }
        else if (type == 3)
        {
            lifeline = lifelines.produceAskAudience(questionNoumber);
        }

        return lifeline;
    }
    
    @Override
    public long getEstimatedTimetoFinishGame()
    {
        return ESTIMATE_TIME_PER_TASK * getNoumberOfTasks();
    }

}
