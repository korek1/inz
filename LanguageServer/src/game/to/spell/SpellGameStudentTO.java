package game.to.spell;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class SpellGameStudentTO extends GameTO {

    List<SpellPairStudentTO> words = new ArrayList<>();

    public SpellGameStudentTO()
    {
        super();
    }

    public SpellGameStudentTO(GameTO gameTO)
    {
        super(gameTO);
    }

    public List<SpellPairStudentTO> getWords()
    {
        return words;
    }

    public void setWords(List<SpellPairStudentTO> words)
    {
        this.words = words;
    }

    public void addPairTO(SpellPairStudentTO pairStudentTO)
    {
        words.add(pairStudentTO);
    }

}
