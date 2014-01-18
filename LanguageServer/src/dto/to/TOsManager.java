package dto.to;

import game.GameResultDBTranslator;

import java.util.Collection;
import java.util.List;

import utils.CommonUtils;
import dto.GameResult;
import dto.Klasa;
import dto.Student;
import dto.Teacher;
import dto.to.gameresult.GameResultClassTO;
import dto.to.gameresult.GameResultTO;
import dto.to.gameresult.GameResultTOs;

public class TOsManager {

    public static StudentTO convertStudent(Student student)
    {
        StudentTO studentTO = new StudentTO();

        int id = student.getId();
        String login = student.getLogin();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        int orderNoumber = student.getOrderNoumber();
        int totalPoints = student.getTotalPoints();
        int klasaID = student.getKlasa().getId();
        boolean female = student.isFemale();

        studentTO.setId(id);
        studentTO.setFirstName(firstName);
        studentTO.setLastName(lastName);
        studentTO.setOrderNoumber(orderNoumber);
        studentTO.setLogin(login);
        studentTO.setTotalPoints(totalPoints);
        studentTO.setKlasaID(klasaID);
        studentTO.setFemale(female);

        return studentTO;
    }

    public static TeacherTO convertTeacher(Teacher teacher)
    {
        TeacherTO teacherTO = new TeacherTO();

        int id = teacher.getId();
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();
        String login = teacher.getLogin();

        teacherTO.setId(id);
        teacherTO.setFirstName(firstName);
        teacherTO.setLastName(lastName);
        teacherTO.setLogin(login);

        return teacherTO;
    }

    public static KlasaTO convertKlasa(Klasa klasa)
    {
        KlasaTO klasaTO = new KlasaTO();

        String name = klasa.getName();
        int id = klasa.getId();
        int year = klasa.getYear();

        List<Student> students = klasa.getStudents();
        for (Student student : students)
        {
            StudentTO studentTO = new StudentTO();

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            int orderNoumber = student.getOrderNoumber();
            int idStudent = student.getId();
            String login = student.getLogin();
            boolean female = student.isFemale();

            studentTO.setFirstName(firstName);
            studentTO.setLastName(lastName);
            studentTO.setOrderNoumber(orderNoumber);
            studentTO.setId(idStudent);
            studentTO.setLogin(login);
            studentTO.setFemale(female);

            klasaTO.addStudentTO(studentTO);
        }

        klasaTO.setId(id);
        klasaTO.setName(name);
        klasaTO.setYear(year);

        return klasaTO;
    }

    public static KlasaTOs convertKlases(Collection<Klasa> klases)
    {
        KlasaTOs klasaTOs = new KlasaTOs();

        for (Klasa klasa : klases)
        {
            KlasaTO klasaTO = new KlasaTO();

            int id = klasa.getId();
            String name = klasa.getName();
            int year = klasa.getYear();

            klasaTO.setId(id);
            klasaTO.setName(name);
            klasaTO.setYear(year);

            klasaTOs.addKlasaTO(klasaTO);

        }

        return klasaTOs;
    }

    public static GameResultTOs convertGameResultTO(List<GameResult> gameResults)
    {
        GameResultTOs gameResultTOs = new GameResultTOs();

        for (GameResult gameResult : gameResults)
        {
            GameResultTO gameResultTO = GameResultDBTranslator.fromDB(gameResult);
            gameResultTOs.addGameResultTO(gameResultTO);
        }

        return gameResultTOs;
    }

    public static GameResultClassTO convertGameResultClassTO(Student student, GameResult gameResult)
    {
        GameResultClassTO gameResultClassTO = null;

        if (CommonUtils.isNotNull(gameResult))
        {
            GameResultTO gameResultTO = GameResultDBTranslator.fromDB(gameResult);
            gameResultClassTO = new GameResultClassTO(gameResultTO);
        }
        else
        {
            gameResultClassTO = new GameResultClassTO(new GameResultTO());
        }

        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        int orderNoumber = student.getOrderNoumber();
        int id = student.getId();

        gameResultClassTO.setStudentID(id);
        gameResultClassTO.setStudentName(firstName);
        gameResultClassTO.setStudentLastName(lastName);
        gameResultClassTO.setStudentOrderNoumber(orderNoumber);

        return gameResultClassTO;
    }

}
