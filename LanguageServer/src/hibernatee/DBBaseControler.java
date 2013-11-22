package hibernatee;

import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DBBaseControler {

    private SessionFactory sessionFactory;

    private SessionFactory getSessionFactory()
    {
        SessionFactory sf = null;

        if (sessionFactory == null)
        {

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegisty = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegisty);

        }

        sf = sessionFactory;

        return sf;
    }

    public Session getSession()
    {

//        return getSessionFactory().getCurrentSession();
        return getSessionFactory().openSession();
    }
    
    public Transaction beginTransaction()
    {
        Transaction beginTransaction = getSession().beginTransaction();
        
        return beginTransaction;
    }

    public void save(Object... objects)
    {
//        Session session = getSession();
        Session session = getSession();
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();

            for (Object o : objects)
            {

                session.save(o);

            }

            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally
        {
            session.close();
        }
       // beginTransaction();
        
//        for (Object o : objects)
//          {
//
//              session.save(o);
//
//          }
//        
//        commit();

    }
    
    public void commit()
    {
        Transaction transaction = getSession().getTransaction();
        
        try
        {
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally
        {
            getSession().close();
        }
    }

    public void update(Object... objects)
    {
        Session session = getSession();
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();

            for (Object o : objects)
            {

                session.update(o);

            }

            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }
    
    public List query(String q)
    {
        Query query = getSession().createQuery(q);
        List results = query.list();
        
        return results;
    }
}
