package spring.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.game.GameDAO;
import spring.game.GameManager;
import spring.teacher.TeacherDAO;
import dto.Game;
import dto.Teacher;
import dto.games.RozsypankaGame;

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
        Integer teachersId = teacherDAO.getTeachersIdByLogin(login);
        Teacher teacher = teacherDAO.load(teachersId);
        game.setOwner(teacher);

        gameDAO.save(game);

    }

    @Override
    @Transactional
    public RozsypankaGame getRozsypankaById(int gameID)
    {
        RozsypankaGame game = (RozsypankaGame) gameDAO.getById(gameID, RozsypankaGame.class);
        game.getSentences().size(); //TODO ?
        
        return game;
    }
    
    @Override
    @Transactional
    public List<Game> getAllGames(String login, Class<? extends Game> clazz)
    {
        List<Game> games = gameDAO.getAllGames(login, clazz);
        
        return games;
    }

}
