package spring.student.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.student.StudentDAO;
import dto.Student;

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
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

}
