package dto.to.toserver;

import java.util.Calendar;

import auth.EncryptHelper;
import dto.Student;

public class TOsInsertManager {

    public static Student convertStudentTO(StudentInsertTO studentInsertTO, String klasName)
    {
        Student student = new Student();

        String firstName = studentInsertTO.getFirstName();
        String lastName = studentInsertTO.getLastName();
        int orderNoumber = studentInsertTO.getOrderNoumber();
        String password = studentInsertTO.getPassword();
        boolean female = studentInsertTO.isFemale();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setOrderNoumber(orderNoumber);
        student.setLogin(createLoginForStudent(lastName, orderNoumber, klasName));
        student.setPassword(EncryptHelper.createEncryptedPass(password));
        student.setFemale(female);

        return student;

    }

    private static String createLoginForStudent(String lastName, int orderNoumber, String klasName)
    {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String string = Integer.toString(year);
        String substring = string.substring(2);

        String login = lastName + klasName + orderNoumber + substring;

        return login;
    }

}
