package dto.to;

import java.util.ArrayList;
import java.util.List;

public class KlasaTO {

    private int id;
    private String name;
    private int year;
    private List<StudentTO> students;

    public KlasaTO()
    {
        super();
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

    public List<StudentTO> getStudents()
    {
        return students;
    }

    public void setStudents(List<StudentTO> students)
    {
        this.students = students;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void addStudentTO(StudentTO studentTO)
    {

        if (students == null)
        {
            students = new ArrayList<>();
        }

        students.add(studentTO);
    }

}
