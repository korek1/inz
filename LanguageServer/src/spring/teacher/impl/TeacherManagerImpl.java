package spring.teacher.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.teacher.TeacherDAO;
import spring.teacher.TeacherManager;
import dto.Game;
import dto.Teacher;

@Service
public class TeacherManagerImpl implements TeacherManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    @Transactional
    public void insertTeacher(Teacher teacher)
    {
        teacherDAO.save(teacher);
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

}
