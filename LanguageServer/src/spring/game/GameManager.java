package spring.game;

import dto.Game;

public interface GameManager {
    
    void insertGame(Game game, String login);

    Game getGameById(int gameID, Class<?> clazz);

}
