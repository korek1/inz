package game.helpers;

import game.to.wordsearch.MappedLetterTO;
import game.to.wordsearch.WordSearchBordTO;
import game.to.wordsearch.WordSearchRowTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordSearchBordCreator
{
    private static final int DEFAULT_WIDTH = 15;
    private static final int DEFAULT_HEIGTH = 8;

    private List<String> words;
    private IntGenerator intGenerator;
    private List<List<MappedLetterTO>> mappedWords = new ArrayList<>();
    private List<List<Integer>> solution = new ArrayList<>();
    private Random rand = new Random();

    public WordSearchBordCreator(List<String> words)
    {
        super();
        this.words = words;
    }

    public WordSearchBordTO createBord()
    {
        return createBord(DEFAULT_WIDTH, DEFAULT_HEIGTH);
    }

    public WordSearchBordTO createBord(int width, int height)
    {
        WordSearchBordTO bordTO = new WordSearchBordTO();

        intGenerator = new IntGenerator(width * height * 1000);

        prepareMappedWords();

        while (height-- > 0)
        {
            WordSearchRowTO row = createRow(width);
            bordTO.addRow(row);
        }

        return bordTO;
    }

    private WordSearchRowTO createRow(int width)
    {
        WordSearchRowTO wordSearchRowTO = new WordSearchRowTO();

        List<MappedLetterTO> mappedWord = null;
        if (!mappedWords.isEmpty())
        {
            mappedWord = mappedWords.get(0);

            putMappedWordToRow(mappedWord, wordSearchRowTO, width);

            mappedWords.remove(mappedWord);
        }
        else
        {
            for (int i = 0; i < width; i++)
            {
                putRandLetterToRow(wordSearchRowTO);
            }
        }

        return wordSearchRowTO;

    }

    private void putRandLetterToRow(WordSearchRowTO wordSearchRowTO)
    {
        char randLetter = randLetter();
        Integer uniqueInt = intGenerator.getNextRandomUniqueInt();

        MappedLetterTO mappedLetterTO = new MappedLetterTO(randLetter, uniqueInt);
        wordSearchRowTO.addMappedLeter(mappedLetterTO);
    }

    private void putMappedWordToRow(List<MappedLetterTO> mappedWord, WordSearchRowTO wordSearchRowTO, int width)
    {
        int size = mappedWord.size();
        int range = width - size;

        int startPuttingFrom = rand.nextInt(range + 1);
        for (int i = 0; i < width; i++)
        {
            if (i >= startPuttingFrom && !mappedWord.isEmpty())
            {
                MappedLetterTO mappedLetterTO = mappedWord.get(0);
                wordSearchRowTO.addMappedLeter(mappedLetterTO);
                mappedWord.remove(mappedLetterTO);
            }
            else
            {
                putRandLetterToRow(wordSearchRowTO);
            }
        }
    }

    private char randLetter()
    {
        int intChar = rand.nextInt(25) + 65;

        char letter = (char) intChar;

        return letter;
    }

    private void prepareMappedWords()
    {
        for (String word : words)
        {
            prepareWord(word);
        }

        Collections.shuffle(mappedWords);
    }

    private void prepareWord(String word)
    {
        word = word.toUpperCase();

        char[] lettersChar = word.toCharArray();

        List<MappedLetterTO> mappedWord = new ArrayList<>();
        List<Integer> partOfSolution = new ArrayList<>();
        for (int i = 0; i < lettersChar.length; i++)
        {
            char c = lettersChar[i];
            Integer randomUniqueInt = intGenerator.getNextRandomUniqueInt();

            partOfSolution.add(randomUniqueInt);
            MappedLetterTO mappedLetter = new MappedLetterTO(c, randomUniqueInt);
            mappedWord.add(mappedLetter);
        }

        solution.add(partOfSolution);
        mappedWords.add(mappedWord);

    }

    public List<List<Integer>> getSolution()
    {
        return solution;
    }
}