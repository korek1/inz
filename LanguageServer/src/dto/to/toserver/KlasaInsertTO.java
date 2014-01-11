package dto.to.toserver;

public class KlasaInsertTO {

    private String name;

    private int year;

    private KlasaInsertTO()
    {
        super();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

}
