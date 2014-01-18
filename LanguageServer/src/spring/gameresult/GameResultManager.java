package spring.gameresult;

import dto.GameResult;
import dto.to.PointsTO;
import dto.to.gameresult.GameResultClassTOs;
import dto.to.gameresult.GameResultTOs;

public interface GameResultManager {

    int saveOrUpdateGameResult(GameResult gameResult, String login);

    GameResultTOs getStudentsGamesResult(int studentID);

    GameResultClassTOs getClassGameResults(int classID, int gameID);

    void addPoints(int points, String login);

    void spendPoints(int points, String login);

    PointsTO getPoints(String login);

}
