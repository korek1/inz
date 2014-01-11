package spring.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auth.Auth;
import spring.login.LoginDAO;
import spring.login.LoginManager;
import utils.CommonUtils;

@Service
public class LoginManagerImpl implements LoginManager {

    @Autowired
    LoginDAO loginDAO;

    @Override
    @Transactional
    public boolean validateTeacher(Auth auth)
    {

        String teacherPassFromDB = loginDAO.getTeacherPass(auth.getLogin());

        boolean correct = passCorrect(teacherPassFromDB, auth.getPass());

        return correct;
    }

    @Override
    @Transactional
    public boolean validateStudent(Auth auth)
    {
        String studentPassFromDB = loginDAO.getStudentPass(auth.getLogin());

        boolean correct = passCorrect(studentPassFromDB, auth.getPass());

        return correct;
    }

    private boolean passCorrect(String fromDB, String fromRequest)
    {
        boolean correct = false;

        if (CommonUtils.isNotNull(fromDB))
        {
            if (fromDB.equals(fromRequest))
            {
                correct = true;
            }
        }

        return correct;
    }

}
