package spring.student.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.student.StudentDAO;
import dto.Student;
import dto.Teacher;

@Service
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getStudents()
    {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Student.class);
              return criteria.list();
    }


    @Override
    public String getMyTeachersLogin(String login)
    {
        List<?> list = sessionFactory.getCurrentSession().createCriteria(Teacher.class,"t")
        .createAlias("t.klasy", "k")
        .createAlias("k.students", "s")
        .add(Restrictions.eq("s.login", login))
        .setProjection(Projections.property("login"))
        .list();
        
        String x = (String) list.get(0);
        System.out.println(x);
        
        return x ;
    }


    @Override
    public Student getByLogin(String login)
    {
        Student student = null;
        
        @SuppressWarnings("unchecked")
        List<Student> list = sessionFactory.getCurrentSession().createCriteria(Student.class, "s")
        .add(Restrictions.eq("s.login", login))
        .list();
        
        if(!list.isEmpty())
        {
            student = list.get(0);
        }
        
        return student;
    }

}
