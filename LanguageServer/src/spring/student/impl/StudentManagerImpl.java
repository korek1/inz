package spring.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.klasa.KlasaDAO;
import spring.student.StudentDAO;
import spring.student.StudentManager;
import dto.Klasa;
import dto.Student;

@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private KlasaDAO klasaDAO;

    @Override
    @Transactional
    public void insertStudent(Student student, String login, int idKlasy)
    {
        Klasa klasa = klasaDAO.get(idKlasy);

        student.setKlasa(klasa);
        klasa.getStudents().add(student);

        studentDAO.save(student);

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

}
