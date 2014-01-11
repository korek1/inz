package spring.teacher;

import spring.dao.BaseDAO;
import dto.Teacher;

public interface TeacherDAO extends BaseDAO<Teacher> {

    Teacher getTeacherByLogin(String login);

    Integer getTeachersIdByLogin(String login);
}
