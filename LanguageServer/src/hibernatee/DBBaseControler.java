package hibernatee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

            ServiceRegistry serviceRegisty = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegisty);

        }

        sf = sessionFactory;

        return sf;
    }

    public Session getSession()
    {

        return getSessionFactory().openSession();
    }

}
