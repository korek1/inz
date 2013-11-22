package spring.teacher.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.teacher.TeacherDAO;
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
}
