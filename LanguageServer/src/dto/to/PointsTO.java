package dto.to;

public class PointsTO {

    private int total;
    private int available;
    private int last;

    public PointsTO()
    {
        super();
    }

    public PointsTO(int total, int available, int last)
    {
        super();
        this.total = total;
        this.available = available;
        this.last = last;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getLast()
    {
        return last;
    }

    public void setLast(int last)
    {
        this.last = last;
    }

    public int getAvailable()
    {
        return available;
    }

    public void setAvailable(int available)
    {
        this.available = available;
    }

}
