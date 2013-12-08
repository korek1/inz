package game.to.millionaire;

import game.to.GameTO;

import java.util.ArrayList;
import java.util.List;

public class MillionaireGameTO extends GameTO {

    private List<MillionaireQuestionTO> questions = new ArrayList<>();

    public MillionaireGameTO()
    {
        super();
    }

    public List<MillionaireQuestionTO> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<MillionaireQuestionTO> questions)
    {
        this.questions = questions;
    }

    public void addQuestion(MillionaireQuestionTO millionaireQuestionTO)
    {
        questions.add(millionaireQuestionTO);
    }

}
