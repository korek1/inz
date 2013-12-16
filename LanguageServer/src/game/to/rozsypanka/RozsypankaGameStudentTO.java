package game.to.rozsypanka;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class RozsypankaGameStudentTO extends GameTO{

    private List<MappedWordTOs> sentences = new ArrayList<>();

    public RozsypankaGameStudentTO()
    {
        super();
    }

    public RozsypankaGameStudentTO(List<List<MappedWordTO>> sentences)
    {
        super();
        for (List<MappedWordTO> list : sentences)
        {
            MappedWordTOs os = new MappedWordTOs();
            os.setSentence(list);
            this.sentences.add(os);
        }
    }

    public List<MappedWordTOs> getSentences()
    {
        return sentences;
    }

    public void setSentences(List<MappedWordTOs> sentences)
    {
        this.sentences = sentences;
    }

}
