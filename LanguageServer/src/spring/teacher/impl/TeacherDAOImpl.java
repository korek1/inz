package spring.teacher.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.teacher.TeacherDAO;
import dto.Game;
import dto.Teacher;

@Service
public class TeacherDAOImpl extends BaseDAOImpl<Teacher> implements TeacherDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Teacher getTeacherByLogin(String login)
    {
        @SuppressWarnings("unchecked")
        List<Teacher> list = sessionFactory.getCurrentSession().createCriteria(Teacher.class).add(Restrictions.eq("login", login)).list();

        return list.get(0);
    }

    @Override
    public Set<Game> getAllGames(String login)
    {
        Teacher teacher = getTeacherByLogin(login);
        Set<Game> games = teacher.getGames();
        
        games.size(); //TODO ?
        
        return games;
    }
}
