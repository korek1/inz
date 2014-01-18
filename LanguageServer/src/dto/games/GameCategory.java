package dto.games;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum GameCategory
{
    @XmlEnumValue("ogólna")
    GENERAL(0, "ogólna"),

    @XmlEnumValue("cz³owiek")
    HUMAN(1, "cz³owiek"),

    @XmlEnumValue("dom")
    HOUSE(2, "dom"),

    @XmlEnumValue("szko³a")
    SHOOL(3, "szko³a"),

    @XmlEnumValue("praca")
    WORK(4, "praca"),

    @XmlEnumValue("¿ycie towarzyskie")
    SOCIAL_LIFE(5, "¿ycie towarzyskie"),

    @XmlEnumValue("¿ywienie")
    NUTRICTION(6, "¿ywienie"),

    @XmlEnumValue("zakupy")
    SHOPPING(7, "zakupy"),

    @XmlEnumValue("podró¿e")
    TRAVEL(8, "podró¿e"),

    @XmlEnumValue("kultura")
    CULTURE(9, "kultura"),

    @XmlEnumValue("sport")
    SPORT(10, "sport"),

    @XmlEnumValue("zdrowie")
    HEALTH(11, "zdrowie"),

    @XmlEnumValue("nauka")
    SCIENCE(12, "nauka"),

    @XmlEnumValue("œrodowisko")
    ENVIROMENT(13, "œrodowisko");

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
