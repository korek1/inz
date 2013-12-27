package game.to.wordsearch;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class WordSearchGameTO extends GameTO {

    private List<String> angWords = new ArrayList<>();
    private List<String> polWords = new ArrayList<>();

    public WordSearchGameTO()
    {
        super();
    }

    public List<String> getAngWords()
    {
        return angWords;
    }

    public void setAngWords(List<String> words)
    {
        this.angWords = words;
    }

    public List<String> getPolWords()
    {
        return polWords;
    }

    public void setPolWords(List<String> polWords)
    {
        this.polWords = polWords;
    }

    public void addAngWord(String word)
    {
        angWords.add(word);
    }

    public void addPolWord(String word)
    {
        polWords.add(word);
    }

}
