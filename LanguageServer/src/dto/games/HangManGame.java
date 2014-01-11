package dto.games;

import javax.persistence.Entity;

import dto.Game;

@Entity
public class HangManGame extends Game {

    private String words;

    public HangManGame()
    {
        super();
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String word)
    {
        this.words = word;
    }

}
