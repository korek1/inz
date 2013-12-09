package game.to.rozsypanka;

import java.util.ArrayList;
import java.util.List;

public class RozsypankaGameStudentTO {

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