package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonUtils {

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

    public static <T> Set<T> ListToSet(List<T> list)
    {
        Set<T> s = new HashSet<>();

        for (T t : list)
        {
            s.add(t);
        }

        return s;

    }

}
