package spring.klasa.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.impl.BaseDAOImpl;
import spring.klasa.KlasaDAO;
import dto.Klasa;

@Service
public class KlasaDAOimpl extends BaseDAOImpl<Klasa> implements KlasaDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Klasa> getAllKlasy(String login)
    {
        
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Klasa.class)
                .createAlias("teacher", "t")
                .add(Restrictions.eq("t.login", login))
                .list();
    }
    
}
