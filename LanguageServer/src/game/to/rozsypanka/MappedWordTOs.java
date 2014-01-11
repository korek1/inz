package game.to.rozsypanka;

import java.util.ArrayList;
import java.util.List;

public class MappedWordTOs {

    List<MappedWordTO> sentence = new ArrayList<>();

    public MappedWordTOs()
    {
        super();
    }

    public List<MappedWordTO> getSentence()
    {
        return sentence;
    }

    public void setSentence(List<MappedWordTO> sentence)
    {
        this.sentence = sentence;
    }

}
