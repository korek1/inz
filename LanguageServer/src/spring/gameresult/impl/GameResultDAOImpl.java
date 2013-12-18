package spring.gameresult.impl;

import game.GameResultDBTranslator;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.gameresult.GameResultDAO;
import dto.GameResult;
import dto.Student;

@Service
public class GameResultDAOImpl extends BaseDAOImpl<GameResult> implements GameResultDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateGameResult(GameResult gameResult, String login)
    {
        @SuppressWarnings("unchecked")
        List<GameResult> list = sessionFactory.getCurrentSession()
                      .createCriteria(GameResult.class)
                      .createAlias("game", "g")
                      .createAlias("student", "s")
                      .add(Restrictions.eq("s.login", login))
                      .add(Restrictions.eq("game.id", gameResult.getGame().getId()))
                      .list();
        
        if(list.isEmpty())
        {
            //trzeba dodaæ caly gameresult
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
            
            String resultToSave = resultDB + resultNEW;
            
            gameResultBD.setResult(resultToSave);
            
            this.save(gameResultBD);
        }
        
    }
    
    
}
