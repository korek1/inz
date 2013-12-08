package game.to.rozsypanka;

public class MappedWordTO {

    private Integer mappedValue;
    private String word;

    public MappedWordTO()
    {
        super();
    }

    public MappedWordTO(Integer mappedValue, String word)
    {
        super();
        this.mappedValue = mappedValue;
        this.word = word;
    }

    public Integer getMappedValue()
    {
        return mappedValue;
    }

    public void setMappedValue(Integer mappedValue)
    {
        this.mappedValue = mappedValue;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

}
