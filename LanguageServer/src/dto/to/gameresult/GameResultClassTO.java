package dto.to.gameresult;

public class GameResultClassTO extends GameResultTO {

    private int studentID;
    private String studentName;
    private String studentLastName;
    private int studentOrderNoumber;

    public GameResultClassTO()
    {
        super();
    }

    public GameResultClassTO(GameResultTO gameResultTO)
    {
        super(gameResultTO);
    }

    public int getStudentID()
    {
        return studentID;
    }

    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentLastName()
    {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName)
    {
        this.studentLastName = studentLastName;
    }

    public int getStudentOrderNoumber()
    {
        return studentOrderNoumber;
    }

    public void setStudentOrderNoumber(int studentOrderNoumber)
    {
        this.studentOrderNoumber = studentOrderNoumber;
    }

}
