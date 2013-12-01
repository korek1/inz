package dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CLASSES")
public class Klasa {

    @Id
    @GeneratedValue
    private int id;
   
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "klasa")
    private List<Student> students = new ArrayList<>();

    public Klasa()
    {
        super();
    }

    public Klasa(String name)
    {
        super();
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    
    
    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

}
