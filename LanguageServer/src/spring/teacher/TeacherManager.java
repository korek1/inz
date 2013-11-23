package spring.teacher;

import dto.Teacher;

public interface TeacherManager {

    void insertTeacher(Teacher teacher);

    Teacher getTeacherById(int teacherId);
    
    Teacher getTeacherByLogin(String login);

}
