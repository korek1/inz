package game;

import game.to.GameTO;
import game.to.GameTOs;

public interface GameRest< T extends GameTO, K extends GameTO> {
    
    Integer insertGame(T game, String login);
    
    T getGame(int id);
    
    String deleteGame(int id);
    
    GameTOs getAllGames(String login);
    
    GameTOs getAllGamesForStudent(String login);
    
    K getGameForStudent(String login, int id);
}
