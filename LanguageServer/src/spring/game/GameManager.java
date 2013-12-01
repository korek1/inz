package spring.game;

import java.util.List;

import dto.Game;
import dto.games.RozsypankaGame;

public interface GameManager {
    
    void insertGame(Game game, String login);

    RozsypankaGame getRozsypankaById(int gameID);
    
    List<Game> getAllGames(String login, Class<? extends Game> clazz);

}
