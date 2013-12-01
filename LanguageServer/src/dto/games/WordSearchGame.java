package dto.games;

import javax.persistence.Entity;

import dto.Game;

@Entity
public class WordSearchGame extends Game {

    // wszytskie slowa zapisywane w jednym wierszu z jakims seperatorem
    private String words;

    public WordSearchGame()
    {
        super();
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
    }

}
