package dto.games;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import dto.Game;
import dto.games.model.SpellPair;

@Entity
public class SpellGame extends Game {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
    private List<SpellPair> words = new ArrayList<>();

    public SpellGame()
    {
        super();
    }

    public List<SpellPair> getWords()
    {
        return words;
    }

    public void setWords(List<SpellPair> words)
    {
        this.words = words;
    }

}
