package dto.games;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum GameCategory
{
    @XmlEnumValue("Ogólna")
    GENERAL(0, "Ogólna"),

    @XmlEnumValue("Cz³owiek")
    HUMAN(1, "Cz³owiek"),

    @XmlEnumValue("Dom")
    HOUSE(2, "Dom"),

    @XmlEnumValue("Szko³a")
    SHOOL(3, "Szko³a"),

    @XmlEnumValue("Praca")
    WORK(4, "Praca"),

    @XmlEnumValue("¯ycie towarzyskie")
    SOCIAL_LIFE(5, "¯ycie towarzyskie"),

    @XmlEnumValue("¯ywienie")
    NUTRICTION(6, "¯ywienie"),

    @XmlEnumValue("Zakupy")
    SHOPPING(7, "Zakupy"),

    @XmlEnumValue("Podró¿e")
    TRAVEL(8, "Podró¿e"),

    @XmlEnumValue("Kultura")
    CULTURE(9, "Kultura"),

    @XmlEnumValue("Sport")
    SPORT(10, "Sport"),

    @XmlEnumValue("Zdrowie")
    HEALTH(11, "Zdrowie"),

    @XmlEnumValue("Nauka")
    SCIENCE(12, "Nauka"),

    @XmlEnumValue("Œrodowisko")
    ENVIROMENT(13, "Œrodowisko");

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
