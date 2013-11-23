package auth;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import utils.CommonUtils;

public class AuthMenager {

    private static Map<String, String> loginPassTeachersMap = new TreeMap<>();
    private static Map<String, String> loginPassStudentsMap = new TreeMap<>();

    // student
    public static void addTempPassStudent(String login, String tempPass)
    {
        addTempPass(login, tempPass, loginPassStudentsMap);
    }

    public static boolean veryfiPassStudent(String login, String pass)
    {

        return veryfiPass(login, pass, loginPassStudentsMap);
    }

    public static void dropPassStudent(String login)
    {
        dropPass(login, loginPassStudentsMap);
    }

    // teacher
    public static void addTempPassTeacher(String login, String tempPass)
    {
        addTempPass(login, tempPass, loginPassTeachersMap);
    }

    public static boolean veryfiPassTeacher(String login, String pass)
    {

        return veryfiPass(login, pass, loginPassTeachersMap);
    }

    public static void dropPassTeacher(String login)
    {
        dropPass(login, loginPassTeachersMap);

    }

    // for tests
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

    // ////////////////////////////////////////////////////

    private static void addTempPass(String login, String tempPass, Map<String, String> map)
    {
        map.put(login, tempPass);
    }

    private static boolean veryfiPass(String login, String pass, Map<String, String> map)
    {

        boolean passCorrect = false;
        if (CommonUtils.isNotNull(login) && CommonUtils.isNotNull(pass))
        {
            String storedPass = map.get(login);

            if (pass.equals(storedPass))
            {
                passCorrect = true;
            }
        }
        return passCorrect;

    }

    private static void dropPass(String login, Map<String, String> map)
    {
        map.remove(login);
    }

}
