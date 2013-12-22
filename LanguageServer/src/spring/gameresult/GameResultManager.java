package spring.gameresult;

import dto.GameResult;
import dto.to.gameresult.GameResultTOs;

public interface GameResultManager {
    
    void saveOrUpdateGameResult(GameResult gameResult, String login);
    
    GameResultTOs getStudentsGamesResult(int studentID);

}
