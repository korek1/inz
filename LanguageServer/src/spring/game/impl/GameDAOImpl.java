package spring.game.impl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.game.GameDAO;
import dto.Game;

@Service
public class GameDAOImpl extends BaseDAOImpl<Game> implements GameDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Session getSession()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Game getById(int id, Class<?> clazz)
    {
        Game object = (Game) sessionFactory.getCurrentSession().get(clazz, id);
        return object;
    }


   

}
