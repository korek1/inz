package spring.teacher.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
    public Teacher getTeacherByLogin(String login)
    {
        @SuppressWarnings("unchecked")
        List<Teacher> list = sessionFactory.getCurrentSession().createCriteria(Teacher.class).add(Restrictions.eq("login", login)).list();

        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer getTeachersIdByLogin(String login)
    {
        List<Integer> list = sessionFactory.getCurrentSession()
        .createCriteria(Teacher.class,"t")
        .add(Restrictions.eq("login", login))
        .setProjection(Projections.property("id"))
        .list();
        
        return list.get(0);
    }

}
