package utils;

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

}
