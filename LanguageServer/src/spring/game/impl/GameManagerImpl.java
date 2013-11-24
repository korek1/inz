package spring.game.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.game.GameDAO;
import spring.game.GameManager;
import spring.teacher.TeacherDAO;
import dto.Game;
import dto.RozsypankaGame;
import dto.Teacher;

@Service
public class GameManagerImpl implements GameManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private GameDAO gameDAO;

    @Override
    @Transactional
    public void insertGame(Game game, String login)
    {
        Teacher teacher = teacherDAO.getTeacherByLogin(login);

        teacher.getGames().add(game);
        game.setOwner(teacher);

        gameDAO.save(game);

    }

    @Override
    @Transactional
    public Game getGameById(int gameID, Class<?> clazz)
    {
        //TODO temp !!!!!!!!!!!!!!!!!!!!!!!!!11
        Game byId = gameDAO.getById(gameID, clazz);
        RozsypankaGame x = (RozsypankaGame) byId;
        x.getSentences().size();
        
        return byId;
    }

}
