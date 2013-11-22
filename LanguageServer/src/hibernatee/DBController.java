package hibernatee;

import java.util.List;

import org.hibernate.Query;

import auth.Auth;

public final class DBController extends DBBaseControler {

    private final String passFromStudent = "SELECT s.password from Student s where s.login = :login";
    private final String passFromTeacher = "SELECT t.password from Teacher t where t.login = :login";
    
    private DBController()
    {
    }
    
    private final static DBController ourInstance = new DBController();
    public static DBController getInstance() {
        return ourInstance;
    }
    
    public boolean validateTeacher(Auth auth)
    {
        boolean valid = false;

        if (auth.getLogin() != null && auth.getPass() != null)
        {
            Query query = getSession().createQuery(passFromTeacher);
            query.setParameter("login", auth.getLogin());
            List list = query.list();
            if (!list.isEmpty())
            {
                String passFromDB = (String) list.get(0);

                if (auth.getPass().equals(passFromDB))
                {
                    valid = true;
                }
            }

        }

        return valid;

    }

    public boolean validateStudent(Auth auth)
    {

        boolean valid = false;

        if (auth.getLogin() != null && auth.getPass() != null)
        {
            Query query = getSession().createQuery(passFromStudent);
            query.setParameter("login", auth.getLogin());
            List list = query.list();
            if (!list.isEmpty())
            {
                String passFromDB = (String) list.get(0);

                if (auth.getPass().equals(passFromDB))
                {
                    valid = true;
                }
            }

        }

        return valid;
    }

}
