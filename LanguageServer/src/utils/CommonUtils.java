package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CommonUtils {

    private static final Random RAND = new Random();

    public static boolean isNotNull(Object obj)
    {
        boolean x = true;

        if (obj == null)
        {
            x = false;
        }

        return x;
    }

    public static boolean isNull(Object obj)
    {
        return !isNotNull(obj);
    }

    public static boolean isEmpty(String string)
    {
        boolean empty = true;

        if (isNotNull(string) && !string.isEmpty())
        {
            empty = false;
        }

        return empty;
    }

    public static boolean isNotEmpty(String string)
    {
        return !isEmpty(string);
    }

    public static <T> Set<T> ListToSet(List<T> list)
    {
        Set<T> s = new HashSet<>();

        for (T t : list)
        {
            s.add(t);
        }

        return s;

    }

    public static boolean isSpecialWord(String word)
    {
        boolean special = false;
        String wordLowerCase = word.toLowerCase();

        if (wordLowerCase.equals("a") || wordLowerCase.equals("an") || wordLowerCase.equals("the"))
        {
            special = true;
        }

        return special;

    }

    /**
     * Returns diff in second between two dates.
     * 
     * @param date1
     *            earlier date
     * @param date2
     *            later date
     * @return
     */
    public static long differenceInSecond(Date date1, Date date2)
    {
        return (date2.getTime() - date1.getTime()) / 1000;
    }

    public static int getPercentage(long l1, long l2)
    {

        return (int) ((l1 * 100.0f) / l2);
    }

    public static long getCurrentTimeStamp()
    {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Returns rand from 1 to range inclusive;
     * 
     * @param range
     * @return
     */
    public static int rand(int range)
    {
        return RAND.nextInt(range + 1) + 1;
    }
}
