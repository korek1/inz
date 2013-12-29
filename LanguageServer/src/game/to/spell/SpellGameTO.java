package game.to.spell;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class SpellGameTO extends GameTO {
    
    List<SpellPairTO> words = new ArrayList<>();

    public SpellGameTO()
    {
        super();
    }

    public List<SpellPairTO> getWords()
    {
        return words;
    }

    public void setWords(List<SpellPairTO> words)
    {
        this.words = words;
    }

    public void addPair(SpellPairTO pairTO)
    {
        words.add(pairTO);
    }
    

}
