package game.to.spell;


public class SpellPairStudentTO {

    private String word1;
    private String word2;
    private String polWord;

    public SpellPairStudentTO()
    {
        super();
    }

    public SpellPairStudentTO(String word1, String word2, String polWord)
    {
        super();
        this.word1 = word1;
        this.word2 = word2;
        this.polWord = polWord;
    }

    public String getWord1()
    {
        return word1;
    }

    public void setWord1(String word1)
    {
        this.word1 = word1;
    }

    public String getWord2()
    {
        return word2;
    }

    public void setWord2(String word2)
    {
        this.word2 = word2;
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
