package spring.student;

import java.util.List;

import dto.Student;
import dto.to.toserver.StudentInsertTO;

public interface StudentManager {
    
    void insertStudent(Student student, String login, int idKlasy);
    
    void insertStudent(StudentInsertTO student, String login);
    
    Student getStudentById(int studentId);
      
    List<Student> getStudents();
    
    String getMyTeachersLogin(String login);
}
