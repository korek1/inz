package game.to.hangman;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class HangManStudentTO extends GameTO {

    private List<Integer> wordsLength = new ArrayList<>();

    public HangManStudentTO()
    {
        super();
    }

    public HangManStudentTO(GameTO gameTO)
    {
        super(gameTO);
    }

    public List<Integer> getWordsLength()
    {
        return wordsLength;
    }

    public void setWordsLength(List<Integer> wordsLength)
    {
        this.wordsLength = wordsLength;
    }

    public void addWordLength(int length)
    {
        wordsLength.add(length);
    }

}
