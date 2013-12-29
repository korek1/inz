package game.helpers;

import java.util.ArrayList;
import java.util.List;

public class HangManSolutionHelper {

    public static List<Integer> createPartSolution(String word)
    {
        List<Integer> partSolution = new ArrayList<>();

        String upperWord = word.toUpperCase();

        char[] charArray = upperWord.toCharArray();

        for (char c : charArray)
        {
            partSolution.add(new Integer((int) c));
        }

        return partSolution;
    }

}
