package spring.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auth.Auth;
import auth.EncryptHelper;
import spring.login.LoginDAO;
import spring.login.LoginManager;
import utils.CommonUtils;

@Service
public class LoginManagerImpl implements LoginManager {

    @Autowired
    LoginDAO loginDAO;

    @Override
    @Transactional(readOnly = true)
    public boolean validateTeacher(Auth auth)
    {

        String teacherPassFromDB = loginDAO.getTeacherPass(auth.getLogin());

        boolean correct = passCorrect(teacherPassFromDB, auth.getPass());

        return correct;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateStudent(Auth auth)
    {
        String studentPassFromDB = loginDAO.getStudentPass(auth.getLogin());

        boolean correct = passCorrect(studentPassFromDB, auth.getPass());

        return correct;
    }

    private boolean passCorrect(String fromDB, String fromRequest)
    {
        boolean correct = false;

        if (CommonUtils.isNotEmpty(fromDB))
        {
            correct = EncryptHelper.isPassCorrect(fromRequest, fromDB);

        }

        return correct;
    }

}
