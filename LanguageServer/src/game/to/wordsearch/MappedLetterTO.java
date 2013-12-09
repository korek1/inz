package game.to.wordsearch;

public class MappedLetterTO {

    private Character letter;
    private Integer value;

    public MappedLetterTO()
    {
        super();
    }

    public MappedLetterTO(Character letter, Integer value)
    {
        super();
        this.letter = letter;
        this.value = value;
    }

    public Character getLetter()
    {
        return letter;
    }

    public void setLetter(Character letter)
    {
        this.letter = letter;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

}
