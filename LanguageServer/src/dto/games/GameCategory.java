package dto.games;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum GameCategory
{
    @XmlEnumValue("og�lna")
    GENERAL(0, "og�lna"),

    @XmlEnumValue("cz�owiek")
    HUMAN(1, "cz�owiek"),

    @XmlEnumValue("dom")
    HOUSE(2, "dom"),

    @XmlEnumValue("szko�a")
    SHOOL(3, "szko�a"),

    @XmlEnumValue("praca")
    WORK(4, "praca"),

    @XmlEnumValue("�ycie towarzyskie")
    SOCIAL_LIFE(5, "�ycie towarzyskie"),

    @XmlEnumValue("�ywienie")
    NUTRICTION(6, "�ywienie"),

    @XmlEnumValue("zakupy")
    SHOPPING(7, "zakupy"),

    @XmlEnumValue("podr�e")
    TRAVEL(8, "podr�e"),

    @XmlEnumValue("kultura")
    CULTURE(9, "kultura"),

    @XmlEnumValue("sport")
    SPORT(10, "sport"),

    @XmlEnumValue("zdrowie")
    HEALTH(11, "zdrowie"),

    @XmlEnumValue("nauka")
    SCIENCE(12, "nauka"),

    @XmlEnumValue("�rodowisko")
    ENVIROMENT(13, "�rodowisko");

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
