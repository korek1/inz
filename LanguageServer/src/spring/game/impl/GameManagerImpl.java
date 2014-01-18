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
import dto.Teacher;
import dto.games.HangManGame;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.SpellGame;
import dto.games.WordSearchGame;

@Service
public class GameManagerImpl implements GameManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private GameDAO gameDAO;

    @Override
    @Transactional
    public Integer insertGame(Game game, String login)
    {
        Integer teachersId = teacherDAO.getTeachersIdByLogin(login);
        Teacher teacher = teacherDAO.load(teachersId);
        game.setOwner(teacher);

        Integer id = gameDAO.save(game);

        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public RozsypankaGame getRozsypankaById(int gameID)
    {
        RozsypankaGame game = (RozsypankaGame) gameDAO.getById(gameID, RozsypankaGame.class);
        game.getSentences().size(); // TODO ?

        return game;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public MemoGame getMemoByID(int gameID)
    {
        MemoGame memoGame = (MemoGame) gameDAO.getById(gameID, MemoGame.class);
        memoGame.getPicWordPair().isEmpty();

        return memoGame;
    }

    @Override
    @Transactional(readOnly = true)
    public MillionaireGame getMillionaireByID(int gameID)
    {
        MillionaireGame millionaireGame = (MillionaireGame) gameDAO.getById(gameID, MillionaireGame.class);
        millionaireGame.getQuestions().isEmpty();

        return millionaireGame;
    }

    @Override
    @Transactional(readOnly = true)
    public WordSearchGame getWordSearchByID(int gameID)
    {
        WordSearchGame wordSearchGame = (WordSearchGame) gameDAO.getById(gameID, WordSearchGame.class);

        return wordSearchGame;
    }

    @Override
    @Transactional(readOnly = true)
    public GameTypeEnum getType(int id)
    {
        GameTypeEnum type = gameDAO.getType(id);

        return type;
    }

    @Override
    @Transactional(readOnly = true)
    public HangManGame getHangManByID(int gameID)
    {
        HangManGame hangManGame = (HangManGame) gameDAO.getById(gameID, HangManGame.class);

        return hangManGame;
    }

    @Override
    @Transactional(readOnly = true)
    public SpellGame getSpellGameByID(int gameID)
    {
        SpellGame spellGame = (SpellGame) gameDAO.getById(gameID, SpellGame.class);
        spellGame.getWords().isEmpty();

        return spellGame;
    }

}
