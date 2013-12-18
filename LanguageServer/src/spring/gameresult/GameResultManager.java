package spring.gameresult;

import dto.GameResult;

public interface GameResultManager {
    
    void saveOrUpdateGameResult(GameResult gameResult, String login);

}
