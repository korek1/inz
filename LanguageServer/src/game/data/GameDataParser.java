package game.data;

import game.helpers.IntGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import utils.CommonUtils;

public class GameDataParser {

    public static List<Map<Integer, String>> processRozsypanka(List<String> senstences)
    {
        List<Map<Integer, String>> list = new ArrayList<>();

        for (String sentence : senstences)
        {
            list.add(splitSentenceToWords(sentence));
        }

        return list;
    }

    private static Map<Integer, String> splitSentenceToWords(String sentence)
    {
        Map<Integer, String> mapper = new LinkedHashMap<>();

        String[] split = sentence.split(" ");

        IntGenerator intGenerator = new IntGenerator();
        String temp = "";
        for (String string : split)
        {
            if (CommonUtils.isSpecialWord(string))
            {
                temp = string + " ";
            }
            else
            {
                Integer uniqueInt = intGenerator.getNextRandomUniqueInt();
                String toAdd = temp + string;
                mapper.put(uniqueInt, toAdd);
                temp = "";
            }
        }

        return mapper;
    }

}
