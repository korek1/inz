package auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.org.apache.bcel.internal.util.BCELifier;

public class AuthMenager {

    private static Map<String, String> loginPassTeachersMap = new HashMap<>();
    private static Map<String, String> loginPassStudentsMap = new HashMap<>();

    // student
    public static void addTempPassStudent(String login, String tempPass)
    {
        loginPassStudentsMap.put(login, tempPass);
    }

    public static boolean veryfiPassStudent(String login, String pass)
    {

        boolean passCorrect = false;

        if (login != null && pass != null)
        {
            String storedPass = loginPassStudentsMap.get(login);

            if (pass.equals(storedPass))
            {
                passCorrect = true;
            }
        }

        return passCorrect;

    }

    public static void dropPassStudent(String login)
    {
        loginPassStudentsMap.remove(login);
    }

    // teacher
    public static void addTempPassTeacher(String login, String tempPass)
    {
        loginPassTeachersMap.put(login, tempPass);
    }

    public static boolean veryfiPassTeacher(String login, String pass)
    {

        boolean passCorrect = false;
        if (login != null && pass != null)
        {
            String storedPass = loginPassTeachersMap.get(login);

            if (pass.equals(storedPass))
            {
                passCorrect = true;
            }
        }
        return passCorrect;

    }

    public static void dropPassTeacher(String login)
    {
        loginPassTeachersMap.remove(login);
        
    }

    public static Set<String> onlineAll()
    {
        Set<String> online = new HashSet<>();

        for (Entry<String, String> entry : loginPassStudentsMap.entrySet())
        {
            online.add(entry.getKey() + " - student");
        }

        for (Entry<String, String> entry : loginPassTeachersMap.entrySet())
        {
            online.add(entry.getKey() + " - teacher");
        }

        return online;
    }

}
