package hibernatee;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dto.Teacher;

public class InitDB {

    static DBController db;

    public static void main(String[] args) throws IOException
    {
        db = DBController.getInstance();
        Session session = db.getSession();

        Teacher teacher1 = new Teacher(null, null, "pass", "admin");
        
        try
        {
            Transaction beginTransaction = session.beginTransaction();

            session.save(teacher1);
            
            beginTransaction.commit();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }

}
