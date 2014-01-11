package spring.student;

import java.util.List;

import spring.dao.BaseDAO;
import dto.Student;

public interface StudentDAO extends BaseDAO<Student> {

    List<Student> getStudents();

    String getMyTeachersLogin(String login);

    Student getByLogin(String login);
}
