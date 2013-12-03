package game.to;

import java.util.ArrayList;
import java.util.List;

public class RozsypankaGameTO extends GameTO {

    private List<String> sentences = new ArrayList<>();

    public RozsypankaGameTO()
    {
        super();
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
