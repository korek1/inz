package dto.to;

import java.util.Collection;
import java.util.List;

import dto.Klasa;
import dto.Student;
import dto.Teacher;
import dto.games.GameCategory;

public class TOsManager {
    

    
    
    // public static Student convertStudentTO(StudentTO studentTO)
    // {
    // Student student = new Student();
    //
    // return student;
    // }
    //
    // public static Teacher convertTeacherTO(TeacherTO teacherTO)
    // {
    // Teacher teacher = new Teacher();
    //
    // return teacher;
    // }
    //
    // public static Klasa convertKlasaTO(KlasaTO klasaTO)
    // {
    // Klasa klasa = new Klasa();
    //
    // return klasa;
    // }
    
   

    public static StudentTO convertStudent(Student student)
    {
        StudentTO studentTO = new StudentTO();

        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        int orderNoumber = student.getOrderNoumber();

        studentTO.setId(id);
        studentTO.setFirstName(firstName);
        studentTO.setLastName(lastName);
        studentTO.setOrderNoumber(orderNoumber);

        return studentTO;
    }

    public static TeacherTO convertTeacher(Teacher teacher)
    {
        TeacherTO teacherTO = new TeacherTO();

        int id = teacher.getId();
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        teacherTO.setId(id);
        teacherTO.setFirstName(firstName);
        teacherTO.setLastName(lastName);

        return teacherTO;
    }

    public static KlasaTO convertKlasa(Klasa klasa)
    {
        KlasaTO klasaTO = new KlasaTO();

        String name = klasa.getName();
        int id = klasa.getId();

        List<Student> students = klasa.getStudents();
        for (Student student : students)
        {
            StudentTO studentTO = new StudentTO();

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            int orderNoumber = student.getOrderNoumber();

            studentTO.setFirstName(firstName);
            studentTO.setLastName(lastName);
            studentTO.setOrderNoumber(orderNoumber);

            klasaTO.addStudentTO(studentTO);
        }

        klasaTO.setId(id);
        klasaTO.setName(name);

        return klasaTO;
    }

    public static KlasaTOs convertKlases(Collection<Klasa> klases)
    {
        KlasaTOs klasaTOs = new KlasaTOs();

        for (Klasa klasa : klases)
        {
            KlasaTO klasaTO = new KlasaTO();

            int id = klasa.getId();
            String name = klasa.getName();

            klasaTO.setId(id);
            klasaTO.setName(name);

            klasaTOs.addKlasaTO(klasaTO);

        }

        return klasaTOs;
    }

   

}
