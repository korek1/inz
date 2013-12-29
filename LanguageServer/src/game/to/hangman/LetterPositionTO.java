package game.to.hangman;

import java.util.List;

public class LetterPositionTO {

    List<Integer> letterPosition;

    public LetterPositionTO()
    {
        super();
    }

    public LetterPositionTO(List<Integer> letterPosition)
    {
        super();
        this.letterPosition = letterPosition;
    }

    public List<Integer> getLetterPosition()
    {
        return letterPosition;
    }

    public void setLetterPosition(List<Integer> letterPosition)
    {
        this.letterPosition = letterPosition;
    }

}
