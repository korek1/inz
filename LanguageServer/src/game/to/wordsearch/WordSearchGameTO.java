package game.to.wordsearch;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class WordSearchGameTO extends GameTO {

    private List<String> words = new ArrayList<>();

    public WordSearchGameTO()
    {
        super();
    }

    public List<String> getWords()
    {
        return words;
    }

    public void setWords(List<String> words)
    {
        this.words = words;
    }
    
    public void addWord(String word)
    {
        words.add(word);
    }
    
    
}
