package spring.gameresult;

import java.util.List;

import dto.GameResult;
import dto.to.PointsTO;

public interface GameResultDAO {

    void saveOrUpdateGameResult(GameResult gameResult, String login);

    List<GameResult> getStudentsGamesResults(int studentID);

    GameResult getStudentGamesResult(int studentID, int gameID);

    void savePoints(int points, String login);

    void spendPoints(int points, String login);

    PointsTO getPoints(String login);

}
