package dto.games;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import dto.Game;
import dto.games.model.MillionaireQuestion;

@Entity
public class MillionaireGame extends Game {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
    private List<MillionaireQuestion> questions = new ArrayList<>();

    public MillionaireGame()
    {
        super();
    }

    public List<MillionaireQuestion> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<MillionaireQuestion> questions)
    {
        this.questions = questions;
    }

}
