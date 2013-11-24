package spring.teacher;

import java.util.Set;

import dto.Game;
import dto.Teacher;

public interface TeacherManager {

    void insertTeacher(Teacher teacher);

    Teacher getTeacherById(int teacherId);
    
    Teacher getTeacherByLogin(String login);
    
    Set<Game> getAllGames(String login);

}
