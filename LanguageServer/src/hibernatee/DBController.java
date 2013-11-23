package hibernatee;

import java.util.List;

import org.hibernate.Query;

import auth.Auth;

public final class DBController extends DBBaseControler {

    private DBController()
    {
    }

    private final static DBController ourInstance = new DBController();

    public static DBController getInstance()
    {
        return ourInstance;
    }

}
