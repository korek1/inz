package spring.game;

import game.helpers.GameTypeEnum;

import java.util.List;

import spring.dao.BaseDAO;
import dto.Game;

public interface GameDAO extends BaseDAO<Game> {

    Game getById(int id, Class<?> clazz);

    void update(Game game);

    List<Game> getAllGames(String login, Class<? extends Game> clazz);

    GameTypeEnum getType(int id);

}
