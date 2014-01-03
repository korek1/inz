package game.to.spell;

public class SpellPairTO {

    private String wordOk;
    private String wordWrong;
    private String polWord;

    public SpellPairTO()
    {
        super();
    }

    public SpellPairTO(String wordOk, String wordWrong, String polWord)
    {
        super();
        this.wordOk = wordOk;
        this.wordWrong = wordWrong;
        this.polWord = polWord;
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

    public String getPolWord()
    {
        return polWord;
    }

    public void setPolWord(String polWord)
    {
        this.polWord = polWord;
    }
    
    

}
