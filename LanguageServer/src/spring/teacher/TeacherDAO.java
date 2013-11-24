package spring.teacher;

import java.util.Set;

import spring.dao.BaseDAO;
import dto.Game;
import dto.Teacher;

public interface TeacherDAO extends BaseDAO<Teacher> {
    
    Teacher getTeacherByLogin(String login);
    
    Set<Game> getAllGames(String login);

}
