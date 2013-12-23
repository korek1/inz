package auth;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utils.CommonUtils;
import dto.to.IsOnlineTO;
import dto.to.OnlineTOs;

public class AuthMenager {

    private static Map<String, TempPass> loginPassTeachersMap = new TreeMap<>();
    private static Map<String, TempPass> loginPassStudentsMap = new TreeMap<>();

    private static final Thread REMOVER = inactiveUserRemover();

    private AuthMenager()
    {
        // no instance
    }

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

    private static synchronized void addTempPass(String login, String tempPass, Map<String, TempPass> map)
    {
        map.put(login, new TempPass(tempPass));
    }

    private static boolean veryfiPass(String login, String pass, Map<String, TempPass> map)
    {

        boolean passCorrect = false;
        if (CommonUtils.isNotNull(login) && CommonUtils.isNotNull(pass))
        {
            TempPass storedPass = map.get(login);

            String tempPassString = null;
            if (CommonUtils.isNotNull(storedPass))
            {
                tempPassString = storedPass.getTempPass();
            }

            if (pass.equals(tempPassString))
            {
                passCorrect = true;
                storedPass.updateTimeStamp();
            }
        }
        return passCorrect;

    }

    private static void dropPass(String login, Map<String, TempPass> map)
    {
        map.remove(login);
    }

    private static Thread inactiveUserRemover()
    {
        Thread x = new Thread(new Runnable(){

            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(180000); // 3 min
                    }
                    catch (InterruptedException e)
                    {
                        // do nothing
                    }

                    removeInactive();
                }
            }
        });

        x.start();

        return x;
    }

    private static synchronized void removeInactive()
    {
        Set<String> teachersToRemove = new HashSet<>();
        Set<String> studentsToRemove = new HashSet<>();
        Set<String> teacherKeySet = loginPassTeachersMap.keySet();

        for (String key : teacherKeySet)
        {
            TempPass tempPass = loginPassTeachersMap.get(key);
            if (!tempPass.isValid())
            {
                teachersToRemove.add(key);
            }
        }

        Set<String> StudentKeySet = loginPassStudentsMap.keySet();

        for (String key : StudentKeySet)
        {
            TempPass tempPass = loginPassStudentsMap.get(key);
            if (!tempPass.isValid())
            {
                studentsToRemove.add(key);
            }
        }

        for (String login : teachersToRemove)
        {
            dropPassTeacher(login);
        }

        for (String login : studentsToRemove)
        {
            dropPassStudent(login);
        }
    }

    public static OnlineTOs checkWhoIsOnline(IsOnlineTO logins)
    {
        OnlineTOs onlineTOs = new OnlineTOs();

        for (String login : logins.getLogins())
        {
            if (loginPassStudentsMap.containsKey(login))
            {
                onlineTOs.addOnline(login);
            }
            else
            {
                onlineTOs.addOffline(login);
            }
        }

        return onlineTOs;
    }

}
