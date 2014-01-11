package spring.login;

public interface LoginDAO {

    String getStudentPass(String login);

    String getTeacherPass(String login);

}
