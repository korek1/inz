package dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class RozsypankaGame extends Game {

    @ElementCollection
    private Set<String> sentences = new HashSet<>();

    public RozsypankaGame()
    {
    }

    public RozsypankaGame(String name)
    {
        super(name);
    }

    public Set<String> getSentences()
    {
        return sentences;
    }

    public void setSentences(Set<String> sentences)
    {
        this.sentences = sentences;
    }

}
