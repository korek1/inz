package spring.game.impl;

import game.helpers.GameTypeEnum;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.game.GameDAO;
import dto.Game;

@Service
public class GameDAOImpl extends BaseDAOImpl<Game> implements GameDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Game getById(int id, Class<?> clazz)
    {
        Game object = (Game) sessionFactory.getCurrentSession().get(clazz, id);
        return object;
    }

    @Override
    public List<Game> getAllGames(String login, Class<? extends Game> clazz)
    {
        @SuppressWarnings("unchecked")
        List<Game> list = sessionFactory.getCurrentSession().createCriteria(clazz).createAlias("owner", "o").add(Restrictions.eq("o.login", login)).list();

        return list;
    }

    @Override
    public void update(Game game)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(game);
    }

    @Override
    public GameTypeEnum getType(int id)
    {
        Object load = null;
        GameTypeEnum toReturn = null;
        GameTypeEnum[] values = GameTypeEnum.values();
        for (GameTypeEnum gameTypeEnum : values)
        {
            Class<? extends Game> clazz = gameTypeEnum.getClazz();

            load = sessionFactory.getCurrentSession().get(clazz, id);
            if (load != null)
            {
                toReturn = gameTypeEnum;
                break;
            }
        }

        return toReturn;
    }
}
