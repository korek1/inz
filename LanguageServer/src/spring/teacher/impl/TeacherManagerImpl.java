package spring.teacher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.teacher.TeacherDAO;
import spring.teacher.TeacherManager;
import utils.CommonUtils;
import dto.Teacher;
import dto.to.toserver.TeacherInsertTO;

@Service
public class TeacherManagerImpl implements TeacherManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    @Transactional
    public Integer insertTeacher(Teacher teacher)
    {
        Integer id = teacherDAO.save(teacher);

        return id;
    }

    @Override
    @Transactional
    public Teacher getTeacherById(int teacherId)
    {
        Teacher teacher = teacherDAO.get(teacherId);
        teacher.getKlasy().isEmpty();

        return teacher;
    }

    @Override
    @Transactional
    public Teacher getTeacherByLogin(String login)
    {
        Teacher teacher = teacherDAO.getTeacherByLogin(login);

        return teacher;
    }

    @Override
    @Transactional
    public void updateTeacher(String login, TeacherInsertTO teacherInsertTO)
    {
        Teacher teacher = teacherDAO.getTeacherByLogin(login);

        if (CommonUtils.isNotEmpty(teacherInsertTO.getFirstName()))
        {
            teacher.setFirstName(teacherInsertTO.getFirstName());
        }

        if (CommonUtils.isNotEmpty(teacherInsertTO.getLastName()))
        {
            teacher.setLastName(teacherInsertTO.getLastName());
        }

        if (CommonUtils.isNotEmpty(teacherInsertTO.getPassword()))
        {
            teacher.setPassword(teacherInsertTO.getPassword());
        }

        teacherDAO.update(teacher);
    }

}
