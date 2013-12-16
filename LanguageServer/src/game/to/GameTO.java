package game.to;


public class GameTO {

    private String name;
    private int id;
    private int categoryId;
    private int difficultyFactor;

    public GameTO()
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getDifficultyFactor()
    {
        return difficultyFactor;
    }

    public void setDifficultyFactor(int difficultyFactor)
    {
        this.difficultyFactor = difficultyFactor;
    }
    
    

}
