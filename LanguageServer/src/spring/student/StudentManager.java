package spring.student;

import java.util.List;

import dto.Student;

public interface StudentManager {
    
    void insertStudent(Student student, String login, int idKlasy);
    
    Student getStudentById(int studentId);
      
    List<Student> getStudents();
}
