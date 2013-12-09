package game.to.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class WordSearchRowTO {

    private List<MappedLetterTO> row = new ArrayList<>();

    public WordSearchRowTO()
    {
        super();
    }

    public List<MappedLetterTO> getRow()
    {
        return row;
    }

    public void setRow(List<MappedLetterTO> row)
    {
        this.row = row;
    }

    public void addMappedLeter(MappedLetterTO mappedLetterTO)
    {
        row.add(mappedLetterTO);
    }

}
