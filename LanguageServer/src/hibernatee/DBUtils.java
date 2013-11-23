package hibernatee;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import utils.CommonUtils;
import dto.Klasa;
import dto.Student;
import dto.Tamagotchi;
import dto.Teacher;

public class DBUtils {

    public static void connectStudentsToClass(Klasa klasa, Student... students)
    {
        for (Student student : students)
        {
            student.setKlasa(klasa);
            klasa.getStudents().add(student);
        }
    }

    public static void connectStudentToAnimal(Student student, Tamagotchi animal)
    {
        student.setAnimal(animal);
        animal.setOwner(student);
    }

    public static void connectClassesToTeacher(Teacher teacher, Klasa... klasas)
    {
        for (Klasa klasa : klasas)
        {
            teacher.getKlasy().add(klasa);
            klasa.setTeacher(teacher);

        }
    }

    public static <T> T initializeAndUnproxy(T entity)
    {
        if (entity == null)
        {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy)
        {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }

    public static void cleanStudent(Student student)
    {
        Tamagotchi animal = student.getAnimal();
        if (CommonUtils.isNotNull(animal))
        {
            animal.setOwner(null);
        }

        // klasa should never be null
        student.getKlasa().setStudents(null);
        student.getKlasa().setTeacher(null);

        student.setGameHistory(null);
    }

    public static void cleanTeacher(Teacher teacher)
    {

        teacher.setGames(null);

        Set<Klasa> klasy = teacher.getKlasy();
        for (Klasa klasa : klasy)
        {
            klasa.setStudents(null);
            klasa.setTeacher(null);

        }
    }

    public static void cleanKlasa(Klasa klasa)
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

    public static void cleanKlasy(List<Klasa> klasas)
    {
        for (Klasa klasa : klasas)
        {
            klasa.setTeacher(null);
            klasa.setStudents(null);
        }
    }

}
