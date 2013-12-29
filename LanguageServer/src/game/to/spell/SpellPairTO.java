package game.to.spell;

public class SpellPairTO {

    private String wordOk;
    private String wordWrong;

    public SpellPairTO()
    {
        super();
    }

    public SpellPairTO(String wordOk, String wordWrong)
    {
        super();
        this.wordOk = wordOk;
        this.wordWrong = wordWrong;
    }

    public String getWordOk()
    {
        return wordOk;
    }

    public void setWordOk(String wordOk)
    {
        this.wordOk = wordOk;
    }

    public String getWordWrong()
    {
        return wordWrong;
    }

    public void setWordWrong(String wordWrong)
    {
        this.wordWrong = wordWrong;
    }

}
