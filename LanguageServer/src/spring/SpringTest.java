package spring;

import spring.student.StudentManager;
import dto.Student;

public class SpringTest {

    public static void main(String[] args)
    {
        StudentManager userManager = (StudentManager) BeanHelper.getBean("studentMenagerImpl");

        Student student = userManager.getStudentById(2);

        Student student1 = new Student("jhgfd", "lastName", 765, "password", "login12345");

        userManager.insertStudent(student1);

        System.out.println(student.getLastName());

        System.out.println("hehs");
    }

}
