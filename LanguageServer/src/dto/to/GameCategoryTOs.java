package dto.to;

import java.util.ArrayList;
import java.util.List;

public class GameCategoryTOs {

    private List<GameCategoryTO> categories = new ArrayList<>();

    public GameCategoryTOs()
    {
        super();
    }

    public List<GameCategoryTO> getCategories()
    {
        return categories;
    }

    public void setCategories(List<GameCategoryTO> categories)
    {
        this.categories = categories;
    }

    public void addCategoryTO(GameCategoryTO gameCategoryTO)
    {
        categories.add(gameCategoryTO);
    }

}
