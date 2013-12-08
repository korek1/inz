package dto.games;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import dto.Game;
import dto.games.model.PicWordPair;

@Entity
public class MemoGame extends Game {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
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
