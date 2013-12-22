package spring.gameresult;

import java.util.List;

import dto.GameResult;

public interface GameResultDAO {

    void saveOrUpdateGameResult(GameResult gameResult, String login);

    List<GameResult> getStudentsGamesResults(int studentID);

    GameResult getStudentGamesResult(int studentID, int gameID);

}
