package spring.game;

import game.helpers.GameTypeEnum;

import java.util.List;

import dto.Game;
import dto.GameResult;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.WordSearchGame;

public interface GameManager {
    
    void insertGame(Game game, String login);

    void update(Game game);
    
    List<Game> getAllGames(String login, Class<? extends Game> clazz);

    RozsypankaGame getRozsypankaById(int gameID);
    
    MemoGame getMemoByID(int gameID);

    MillionaireGame getMillionaireByID(int gameID);
    
    WordSearchGame getWordSearchByID(int gameID);
    
    GameTypeEnum getType(int id);

}
