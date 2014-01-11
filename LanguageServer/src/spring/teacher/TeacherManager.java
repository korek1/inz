package spring.teacher;

import dto.Teacher;
import dto.to.toserver.TeacherInsertTO;

public interface TeacherManager {

    Integer insertTeacher(Teacher teacher);

    Teacher getTeacherById(int teacherId);

    Teacher getTeacherByLogin(String login);

    void updateTeacher(String login, TeacherInsertTO teacherInsertTO);

}
