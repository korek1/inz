package spring.game;

import spring.dao.BaseDAO;
import dto.Game;

public interface GameDAO extends BaseDAO<Game> {

    Game getById(int id, Class<?> clazz);
    
}
