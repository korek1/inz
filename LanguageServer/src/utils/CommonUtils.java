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
    
    public static boolean isEmpty(String string)
    {
        boolean empty = true;
        
        if(isNotNull(string) && !string.isEmpty())
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
        
        if(wordLowerCase.equals("a") || wordLowerCase.equals("an") || wordLowerCase.equals("the"))
        {
            special = true;
        }
        
        return special;
        
    }
    
    

}
