package spring.student.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import animal.AnimalDirHelper;
import auth.AuthMenager;
import auth.EncryptHelper;
import spring.klasa.KlasaDAO;
import spring.student.StudentDAO;
import spring.student.StudentManager;
import utils.CommonUtils;
import dto.Klasa;
import dto.Student;
import dto.to.AvatarTO;
import dto.to.AvatarTOs;
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
    @Transactional(readOnly = true)
    public Student getStudentById(int studentId)
    {
        Student student = studentDAO.get(studentId);
        student.getKlasa().getId();

        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudents()
    {
        return studentDAO.getStudents();
    }

    @Override
    @Transactional(readOnly = true)
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
            student.setPassword(EncryptHelper.createEncryptedPass(studentTO.getPassword()));
        }

        studentDAO.update(student);

    }

    @Override
    @Transactional
    public void changePass(String newPass, String login)
    {
        Student student = studentDAO.getByLogin(login);

        student.setPassword(EncryptHelper.createEncryptedPass(newPass));

        studentDAO.update(student);

    }

    @Override
    @Transactional
    public void updateLastAvatarUpdate(String login)
    {
        Student student = studentDAO.getByLogin(login);
        student.setLastAvatarUpdate(new Date());

        studentDAO.update(student);

    }

    @Override
    @Transactional(readOnly = true)
    public AvatarTOs getAvatarsTO(String login)
    {
        AvatarTOs avatarTOs = new AvatarTOs();

        Student student = studentDAO.getByLogin(login);
        Klasa klasa = student.getKlasa();
        List<Student> students = klasa.getStudents();

        for (Student s : students)
        {
            String firstName = s.getFirstName();
            String lastName = s.getLastName();
            int orderNoumber = s.getOrderNoumber();
            Date lastAvatarUpdate = s.getLastAvatarUpdate();
            int totalPoints = s.getTotalPoints();
            String studentLogin = s.getLogin();

            avatarTOs.addAvatarTO(new AvatarTO(firstName, lastName, studentLogin, orderNoumber, totalPoints, lastAvatarUpdate));
        }

        return avatarTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentByLogin(String login)
    {
        Student student = studentDAO.getByLogin(login);
        student.getKlasa().getId();

        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(int id)
    {
        Student student = studentDAO.get(id);
        String login = student.getLogin();

        AuthMenager.dropPassStudent(login);

        try
        {
            AnimalDirHelper.deleteStudentsFiles(login);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        studentDAO.delete(student);
    }
}
