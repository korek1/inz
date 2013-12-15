package dto.games;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum GameCategory
{
    @XmlEnumValue("general")
    GENERAL(0, "general"),

    @XmlEnumValue("human")
    HUMAN(1, "human"),

    @XmlEnumValue("house")
    HOUSE(2, "house"),

    @XmlEnumValue("shool")
    SHOOL(3, "shool"),

    @XmlEnumValue("work")
    WORK(4, "work"),

    @XmlEnumValue("social life")
    SOCIAL_LIFE(5, "social life"),

    @XmlEnumValue("nutriction")
    NUTRICTION(6, "nutriction"),

    @XmlEnumValue("shopping")
    SHOPPING(7, "shopping"),

    @XmlEnumValue("travel")
    TRAVEL(8, "travel"),

    @XmlEnumValue("culture")
    CULTURE(9, "culture"),

    @XmlEnumValue("sport")
    SPORT(10, "sport"),

    @XmlEnumValue("health")
    HEALTH(11, "health"),

    @XmlEnumValue("science")
    SCIENCE(12, "science"),

    @XmlEnumValue("enviroment")
    ENVIROMENT(13, "enviroment");

    public static int validateCategory(int id)
    {
        int categoryId = GENERAL.getId();

        GameCategory[] values = GameCategory.values();
        for (GameCategory gameCategory : values)
        {
            if (gameCategory.getId() == id)
            {
                categoryId = id;
                break;
            }
        }

        return categoryId;
    }

    private GameCategory(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

}
