package game.to.wordsearch;

import game.to.GameTO;

public class WordSearchGameStudentTO extends GameTO {

    private WordSearchBordTO bord;

    public WordSearchGameStudentTO()
    {
        super();
    }

    public WordSearchBordTO getBord()
    {
        return bord;
    }

    public void setBord(WordSearchBordTO bord)
    {
        this.bord = bord;
    }

}
