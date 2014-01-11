package spring.klasa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.klasa.KlasaDAO;
import spring.klasa.KlasaManager;
import spring.student.StudentDAO;
import spring.teacher.TeacherDAO;
import dto.Klasa;
import dto.Student;
import dto.Teacher;

@Service
public class KlasaManagerImpl implements KlasaManager {

    @Autowired
    private KlasaDAO klasaDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public Integer insertKlasa(Klasa klasa, String login)
    {
        Integer teachersId = teacherDAO.getTeachersIdByLogin(login);
        Teacher teacher = teacherDAO.load(teachersId);
        klasa.setTeacher(teacher);

        Integer id = klasaDAO.save(klasa);

        return id;
    }

    @Override
    @Transactional
    public Klasa getKlasaById(int klasaId)
    {
        Klasa klasa = klasaDAO.get(klasaId);
        klasa.getStudents().isEmpty();

        return klasa;
    }

    @Override
    @Transactional
    public List<Klasa> getAllKlasy(String login)
    {

        return klasaDAO.getAllKlasy(login);
    }

    @Override
    @Transactional
    public List<String> getStudentsLogins(String studentLogin)
    {
        List<String> logins = new ArrayList<>();

        Student student = studentDAO.getByLogin(studentLogin);
        Klasa klasa = student.getKlasa();
        List<Student> students = klasa.getStudents();
        for (Student s : students)
        {
            logins.add(s.getLogin());
        }

        return logins;
    }

}
