package spring.login;

import auth.Auth;

public interface LoginManager {

    boolean validateTeacher(Auth auth);

    boolean validateStudent(Auth auth);

}
