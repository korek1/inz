package spring.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.klasa.KlasaDAO;
import spring.student.StudentDAO;
import spring.student.StudentManager;
import utils.CommonUtils;
import dto.Klasa;
import dto.Student;
import dto.to.toserver.StudentInsertTO;
import dto.to.toserver.TOsInsertManager;

@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private KlasaDAO klasaDAO;

    @Override
    @Transactional
    public Integer insertStudent(Student student, String login, int idKlasy)
    {
        Klasa klasa = klasaDAO.load(idKlasy);
        student.setKlasa(klasa);

        Integer id = studentDAO.save(student);

        return id;
    }

    @Override
    @Transactional
    public Student getStudentById(int studentId)
    {
        Student student = studentDAO.get(studentId);

        return student;
    }

    @Override
    @Transactional
    public List<Student> getStudents()
    {
        return studentDAO.getStudents();
    }

    @Override
    @Transactional
    public String getMyTeachersLogin(String login)
    {
        String teachersLogin = studentDAO.getMyTeachersLogin(login);

        return teachersLogin;
    }

    @Override
    @Transactional
    public Integer insertStudent(StudentInsertTO student, String login)
    {
        Klasa klasa = klasaDAO.get(student.getIdKlasy());
        String name = klasa.getName();

        Student studentDB = TOsInsertManager.convertStudentTO(student, name);
        studentDB.setKlasa(klasa);

        Integer id = studentDAO.save(studentDB);
        
        return id;
    }

    @Override
    @Transactional
    public void updateStudent(StudentInsertTO studentTO, int id)
    {
        Student student = studentDAO.load(id);

        if (CommonUtils.isNotNull(studentTO.getFirstName()))
        {
            student.setFirstName(studentTO.getFirstName());
        }
        if (CommonUtils.isNotNull(studentTO.getLastName()))
        {
            student.setLastName(studentTO.getLastName());
        }
        if (studentTO.getIdKlasy() != 0)
        {
            Klasa klasa = klasaDAO.load(studentTO.getIdKlasy());
            student.setKlasa(klasa);
        }
        if (studentTO.getOrderNoumber() != 0)
        {
            student.setOrderNoumber(studentTO.getOrderNoumber());
        }
        if (CommonUtils.isNotNull(studentTO.getPassword()))
        {
            student.setPassword(studentTO.getPassword());
        }

        studentDAO.update(student);

    }

    @Override
    @Transactional
    public void changePass(String newPass, String login)
    {
        Student student = studentDAO.getByLogin(login);
        
        student.setPassword(newPass);
        
        studentDAO.update(student);
        
    }

}
