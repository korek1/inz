package spring.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.student.StudentDAO;
import spring.student.StudentManager;
import dto.Student;

@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public void insertStudent(Student student)
    {
        studentDAO.save(student);

    }

    @Override
    @Transactional
    public Student getStudentById(int studentId)
    {
        Student student = studentDAO.get(studentId);

        return student;
    }

    @Override
    @Transactional
    public List<Student> getStudents()
    {
        return studentDAO.getStudents();
    }

}
