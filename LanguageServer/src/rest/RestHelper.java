package rest;

import hibernatee.DBController;
import hibernatee.DBUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dto.Game;
import dto.Klasa;
import dto.Student;
import dto.Teacher;

public class RestHelper {

    private DBController db = DBController.getInstance();

    public Student getStudentById(int id)
    {
        Session session = db.getSession();

        Student student = (Student) session.get(Student.class, id);

        session.close();

        cleanStudent(student);

        return student;
    }

    public Teacher getTeacherById(int id)
    {
        Session session = db.getSession();
        Teacher teacher = (Teacher) session.get(Teacher.class, id);

        cleanTeacher(teacher);
        session.close();

        return teacher;
    }

    public Klasa getClassById(int id)
    {
        Session session = db.getSession();
        Klasa klasa = (Klasa) session.get(Klasa.class, id);

        cleanKlasa(klasa);
        session.close();

        return klasa;
    }

    public List<Klasa> getAllTeachersClasses(String login)
    {
        Session session = db.getSession();
        Criteria crit = session.createCriteria(Klasa.class);
        crit.createAlias("teacher", "t");
        crit.add(Restrictions.eq("t.login", login));
        List<Klasa> classes = (List<Klasa>) crit.list();

        session.close();

        for (Klasa klasa : classes)
        {
            klasa.setStudents(null);
            klasa.setTeacher(null);
        }

        return classes;
    }

    public void addClass(Klasa klasa, String login)
    {
        Session session = db.getSession();

        SQLQuery addEntity = session.createSQLQuery("SELECT * FROM Teacher t where t.login = :login").addEntity(Teacher.class);

        List<Teacher> list = addEntity.setParameter("login", login).list();
        klasa.setTeacher(list.get(0));
        session.close();

        db.save(klasa);
    }

    public void addStudent(Student student, String login, int idKlasy)
    {
        Session session = db.getSession();

        db.beginTransaction();
        
        Klasa klasa = (Klasa) session.get(Klasa.class, idKlasy);

        student.setKlasa(klasa);

        db.save(student);
    }
    
    public void addTeacher(Teacher teacher)
    {
        db.save(teacher);
    }

    private void cleanStudent(Student student)
    {
        student.getAnimal().setOwner(null);
        student.getKlasa().setStudents(null);
        student.getKlasa().setTeacher(null);
    }

    private void cleanTeacher(Teacher teacher)
    {
        Set<Game> games = teacher.getGames();
        if (games != null)
        {
            for (Game game : games)
            {
                game.setOwner(null);
            }
        }

        Set<Klasa> klasy = teacher.getKlasy();
        for (Klasa klasa : klasy)
        {
            klasa.setStudents(null);
            klasa.setTeacher(null);

        }
    }

    private void cleanKlasa(Klasa klasa)
    {
        klasa.getTeacher().setGames(null);
        klasa.getTeacher().setKlasy(null);

        Set<Student> students = klasa.getStudents();
        for (Student student : students)
        {
            student.setAnimal(null);
            student.setKlasa(null);
            student.setGameHistory(null);
        }
    }

}
