package game.to.hangman;

import java.util.ArrayList;
import java.util.List;

import game.to.GameTO;

public class HangManTO extends GameTO {

    private List<String> words = new ArrayList<>();

    public HangManTO()
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
