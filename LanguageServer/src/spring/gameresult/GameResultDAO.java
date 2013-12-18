package spring.gameresult;

import dto.GameResult;

public interface GameResultDAO {

    void saveOrUpdateGameResult(GameResult gameResult, String login);
    
}
