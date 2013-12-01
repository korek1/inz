package dto.games;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import dto.Game;
import dto.games.model.PicWordPair;

@Entity
public class MemoGame extends Game {

    @ElementCollection
    List<PicWordPair> picWordPair = new ArrayList<>();

    public MemoGame()
    {
        super();
    }

    public List<PicWordPair> getPicWordPair()
    {
        return picWordPair;
    }

    public void setPicWordPair(List<PicWordPair> picWordPair)
    {
        this.picWordPair = picWordPair;
    }

}
