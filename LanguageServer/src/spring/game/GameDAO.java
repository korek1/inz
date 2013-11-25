package spring.game;

import java.util.List;

import spring.dao.BaseDAO;
import dto.Game;

public interface GameDAO extends BaseDAO<Game> {

    Game getById(int id, Class<?> clazz);
    
    List<Game> getAllGames(String login, Class<? extends Game> clazz);
    
}
