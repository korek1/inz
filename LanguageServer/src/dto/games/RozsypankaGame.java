package dto.games;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import dto.Game;

@Entity
public class RozsypankaGame extends Game {

    @ElementCollection
    private List<String> sentences = new ArrayList<>();

    public RozsypankaGame()
    {
        super();
    }

    public RozsypankaGame(String name)
    {
        super(name);
    }

    public List<String> getSentences()
    {
        return sentences;
    }

    public void setSentences(List<String> sentences)
    {
        this.sentences = sentences;
    }

}
