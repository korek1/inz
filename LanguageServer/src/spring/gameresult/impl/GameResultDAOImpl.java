package spring.gameresult.impl;

import game.GameResultDBTranslator;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.gameresult.GameResultDAO;
import spring.student.StudentDAO;
import dto.GameResult;
import dto.Student;
import dto.to.PointsTO;

@Service
public class GameResultDAOImpl extends BaseDAOImpl<GameResult> implements GameResultDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public int saveOrUpdateGameResult(GameResult gameResult, String login)
    {
        @SuppressWarnings("unchecked")
        List<GameResult> list = sessionFactory.getCurrentSession()
                .createCriteria(GameResult.class)
                .createAlias("game", "g")
                .createAlias("student", "s")
                .add(Restrictions.eq("s.login", login))
                .add(Restrictions.eq("game.id", gameResult.getGame().getId()))
                .list();

        String resultToSave = gameResult.getResult();
        if (list.isEmpty())
        {
            // trzeba dodaæ caly gameresult
            @SuppressWarnings("unchecked")
            List<Student> list2 = sessionFactory.getCurrentSession()
                .createCriteria(Student.class)
                .add(Restrictions.eq("login", login))
                .list();

            Student student = list2.get(0);
            gameResult.setStudent(student);

            this.save(gameResult);
        }
        else
        {
            GameResult gameResultBD = list.get(0);
            String resultDB = gameResultBD.getResult();
            String resultNEW = gameResult.getResult();

            resultToSave = resultDB + resultNEW;

            gameResultBD.setResult(resultToSave);

            this.update(gameResultBD);
        }
        
        return resultToSave.split(GameResultDBTranslator.RESUT_SEPARATOR).length - 1;

    }

    @Override
    public List<GameResult> getStudentsGamesResults(int studentID)
    {
        Student student = studentDAO.load(studentID);
        List<GameResult> gameHistory = student.getGameHistory();

        return gameHistory;
    }

    @Override
    public GameResult getStudentGamesResult(int studentID, int gameID)
    {
        GameResult gameResult = null;

        @SuppressWarnings("unchecked")
        List<GameResult> list = sessionFactory.getCurrentSession()
                .createCriteria(GameResult.class)
                .createAlias("game", "g").createAlias("student", "s")
                .add(Restrictions.eq("s.id", studentID))
                .add(Restrictions.eq("game.id", gameID))
                .list();

        if (!list.isEmpty())
        {
            gameResult = list.get(0);
        }

        return gameResult;
    }

    @Override
    public void savePoints(int points, String login)
    {
        Student student = studentDAO.getByLogin(login);

        student.setTotalPoints(student.getTotalPoints() + points);
        student.setLastPoints(points);
        student.setAvailablePoints(student.getAvailablePoints() + points);

        studentDAO.update(student);

    }

    @Override
    public PointsTO getPoints(String login)
    {
        Student student = studentDAO.getByLogin(login);

        int totalPoints = student.getTotalPoints();
        int lastPoints = student.getLastPoints();
        int availablePoints = student.getAvailablePoints();

        return new PointsTO(totalPoints, availablePoints, lastPoints);
    }

    @Override
    public void spendPoints(int points, String login)
    {
        Student student = studentDAO.getByLogin(login);

        student.setAvailablePoints(student.getAvailablePoints() - points);

        studentDAO.update(student);
    }

}
