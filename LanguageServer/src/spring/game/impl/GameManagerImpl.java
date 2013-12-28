package spring.game.impl;

import game.helpers.GameTypeEnum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.game.GameDAO;
import spring.game.GameManager;
import spring.teacher.TeacherDAO;
import dto.Game;
import dto.GameResult;
import dto.Teacher;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.WordSearchGame;

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
        game.getSentences().size(); // TODO ?

        return game;
    }

    @Override
    @Transactional
    public List<Game> getAllGames(String login, Class<? extends Game> clazz)
    {
        List<Game> games = gameDAO.getAllGames(login, clazz);

        return games;
    }

    @Override
    @Transactional
    public void update(Game game)
    {
        gameDAO.update(game);
    }

    @Override
    @Transactional
    public MemoGame getMemoByID(int gameID)
    {
        MemoGame memoGame = (MemoGame) gameDAO.getById(gameID, MemoGame.class);
        memoGame.getPicWordPair().isEmpty();

        return memoGame;
    }

    @Override
    @Transactional
    public MillionaireGame getMillionaireByID(int gameID)
    {
        MillionaireGame millionaireGame = (MillionaireGame) gameDAO.getById(gameID, MillionaireGame.class);
        millionaireGame.getQuestions().isEmpty();

        return millionaireGame;
    }

    @Override
    @Transactional
    public WordSearchGame getWordSearchByID(int gameID)
    {
        WordSearchGame wordSearchGame = (WordSearchGame) gameDAO.getById(gameID, WordSearchGame.class);

        return wordSearchGame;
    }

    @Override
    @Transactional
    public GameTypeEnum getType(int id)
    {
        GameTypeEnum type = gameDAO.getType(id);

        return type;
    }

}
