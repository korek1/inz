package game.impl;

import java.util.List;

import game.CurrentGame;
import dto.Game;

public class CurrentRozsypankaGame extends CurrentGame {
    
    public static final long ESTIMATE_TIME_PER_WORD = 4;

    public CurrentRozsypankaGame(Game game)
    {
        super(game);
    }
    
    @Override
    public long getEstimatedTimetoFinishGame()
    {
        int noumberOfWordsInGame = 0;
        
        for(List<Integer> list : getSolution())
        {
            noumberOfWordsInGame += list.size();
        }
        
        return ESTIMATE_TIME_PER_WORD * noumberOfWordsInGame;
    }

}
