package game.to.wordsearch;

import game.to.GameTO;
import game.to.rozsypanka.MappedWordTO;

import java.util.ArrayList;
import java.util.List;

public class WordSearchGameStudentTO extends GameTO {

    private WordSearchBordTO bord;
    private List<MappedWordTO> polWords = new ArrayList<>();

    public WordSearchGameStudentTO()
    {
        super();
    }

    public WordSearchGameStudentTO(GameTO gameTO)
    {
        super(gameTO);
    }

    public WordSearchBordTO getBord()
    {
        return bord;
    }

    public void setBord(WordSearchBordTO bord)
    {
        this.bord = bord;
    }

    public List<MappedWordTO> getPolWords()
    {
        return polWords;
    }

    public void setPolWords(List<MappedWordTO> polWords)
    {
        this.polWords = polWords;
    }

    public void addMappedPolWord(MappedWordTO mappedWordTO)
    {
        polWords.add(mappedWordTO);
    }

}
