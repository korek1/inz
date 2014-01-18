package spring.gameresult.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.gameresult.GameResultDAO;
import spring.gameresult.GameResultManager;
import spring.klasa.KlasaDAO;
import dto.GameResult;
import dto.Klasa;
import dto.Student;
import dto.to.PointsTO;
import dto.to.TOsManager;
import dto.to.gameresult.GameResultClassTO;
import dto.to.gameresult.GameResultClassTOs;
import dto.to.gameresult.GameResultTOs;

@Service
public class GameResultManagerImpl implements GameResultManager {

    @Autowired
    private GameResultDAO gameResultDAO;

    @Autowired
    private KlasaDAO klasaDAO;

    @Override
    @Transactional
    public void saveOrUpdateGameResult(GameResult gameResult, String login)
    {
        gameResultDAO.saveOrUpdateGameResult(gameResult, login);
    }

    @Override
    @Transactional(readOnly = true)
    public GameResultTOs getStudentsGamesResult(int studentID)
    {
        List<GameResult> studentsGamesResults = gameResultDAO.getStudentsGamesResults(studentID);

        GameResultTOs convertGameResultTO = TOsManager.convertGameResultTO(studentsGamesResults);

        return convertGameResultTO;
    }

    @Override
    @Transactional(readOnly = true)
    public GameResultClassTOs getClassGameResults(int classID, int gameID)
    {
        Klasa klasa = klasaDAO.load(classID);
        List<Student> students = klasa.getStudents();

        GameResultClassTOs convertGameResultClassTOs = new GameResultClassTOs();

        for (Student student : students)
        {
            GameResult studentGamesResult = gameResultDAO.getStudentGamesResult(student.getId(), gameID);

            GameResultClassTO convertGameResultClassTO = TOsManager.convertGameResultClassTO(student, studentGamesResult);

            convertGameResultClassTOs.addGameResultClassTO(convertGameResultClassTO);
        }

        return convertGameResultClassTOs;
    }

    @Override
    @Transactional
    public void addPoints(int points, String login)
    {
        gameResultDAO.savePoints(points, login);

    }

    @Override
    @Transactional(readOnly = true)
    public PointsTO getPoints(String login)
    {
        PointsTO points = gameResultDAO.getPoints(login);

        return points;
    }

    @Override
    @Transactional
    public void spendPoints(int points, String login)
    {
        gameResultDAO.spendPoints(points, login);
    }

}
