package game.to.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class WordSearchBordTO {

    List<WordSearchRowTO> rows = new ArrayList<>();

    public WordSearchBordTO()
    {
        super();
    }

    public List<WordSearchRowTO> getRows()
    {
        return rows;
    }

    public void setRows(List<WordSearchRowTO> rows)
    {
        this.rows = rows;
    }

    public void addRow(WordSearchRowTO rowTO)
    {
        rows.add(rowTO);
    }

}
