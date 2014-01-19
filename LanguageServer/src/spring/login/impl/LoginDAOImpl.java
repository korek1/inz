package spring.login.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.login.LoginDAO;
import utils.CommonUtils;
import dto.Student;
import dto.Teacher;

@Service
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String getStudentPass(String login)
    {
        String pass = getPass(Student.class, login);

        return pass;
    }

    @Override
    public String getTeacherPass(String login)
    {
        String pass = getPass(Teacher.class, login);;

        return pass;
    }

    private String getPass(Class<?> clazz, String login)
    {

        String pass = null;

        @SuppressWarnings("rawtypes")
        List list = sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq("login", login)).setProjection(Projections.property("password")).list();

        Object obj = null;
        if (!list.isEmpty())
        {
            obj = list.get(0);
            if (CommonUtils.isNotNull(obj) && obj instanceof String)
            {
                pass = (String) obj;
            }
        }

        return pass;

    }

}
