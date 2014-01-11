package game.impl;

import utils.CommonUtils;
import dto.Game;
import game.CurrentGame;
import game.helpers.Lifelines;

public class CurrentMillionaireGame extends CurrentGame {

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

}
