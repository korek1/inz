package game.to.millionaire;

import java.util.ArrayList;
import java.util.List;

public class MillionaireQuestionTO {

    private String question;

    private List<String> answers = new ArrayList<>();

    private Integer correctAnswer;

    public MillionaireQuestionTO()
    {
        super();
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public List<String> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<String> answers)
    {
        this.answers = answers;
    }

    public Integer getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    public void addAnswer(String answer)
    {
        answers.add(answer);
    }

}
