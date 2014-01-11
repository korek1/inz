package spring.student;

import java.util.List;

import dto.Student;
import dto.to.AvatarTOs;
import dto.to.toserver.StudentInsertTO;

public interface StudentManager {

    Integer insertStudent(Student student, String login, int idKlasy);

    Integer insertStudent(StudentInsertTO student, String login);

    void updateStudent(StudentInsertTO student, int id);

    void deleteStudent(int id);

    void changePass(String newPass, String login);

    Student getStudentById(int studentId);

    Student getStudentByLogin(String login);

    List<Student> getStudents();

    String getMyTeachersLogin(String login);

    void updateLastAvatarUpdate(String login);

    AvatarTOs getAvatarsTO(String login);
}
