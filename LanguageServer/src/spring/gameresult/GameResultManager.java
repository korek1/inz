package spring.gameresult;

import dto.GameResult;
import dto.to.gameresult.GameResultClassTOs;
import dto.to.gameresult.GameResultTOs;

public interface GameResultManager {
    
    void saveOrUpdateGameResult(GameResult gameResult, String login);
    
    GameResultTOs getStudentsGamesResult(int studentID);
    
    GameResultClassTOs getClassGameResults(int classID, int gameID);

}
