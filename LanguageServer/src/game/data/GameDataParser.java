package game.data;

import game.helpers.IntGenerator;
import game.to.MappedWordTO;

import java.util.ArrayList;
import java.util.List;

import utils.CommonUtils;

public class GameDataParser {

    public static List<List<MappedWordTO>> processRozsypanka(List<String> senstences)
    {
        List<List<MappedWordTO>> list = new ArrayList<>();

        for (String sentence : senstences)
        {
            list.add(splitSentenceToWords(sentence));
        }

        return list;
    }

    private static List<MappedWordTO> splitSentenceToWords(String sentence)
    {
        List<MappedWordTO> mapper = new ArrayList<>();

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

                mapper.add(new MappedWordTO(uniqueInt, toAdd));
                temp = "";
            }
        }

        handleTheSameWords(mapper);

        return mapper;
    }

    private static void handleTheSameWords(List<MappedWordTO> mapper)
    {
        for (MappedWordTO mappedWordTO : mapper)
        {
            for (MappedWordTO mappedWordTOinside : mapper)
            {
                if (!mappedWordTO.equals(mappedWordTOinside))
                {
                    String word = mappedWordTO.getWord();
                    String word2 = mappedWordTOinside.getWord();

                    if (word.equals(word2))
                    {
                        mappedWordTOinside.setMappedValue(mappedWordTO.getMappedValue());
                    }
                }
            }
        }
    }

}
