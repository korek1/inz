package dto.games;

import javax.persistence.Entity;

import dto.Game;

@Entity
public class MillionaireGame extends Game {

    // co tu bedzie ?
    // dalem cos zeby poki co tabela nie byla pusta
    private String xxx;

    public MillionaireGame()
    {
        super();
    }

    public String getXxx()
    {
        return xxx;
    }

    public void setXxx(String xxx)
    {
        this.xxx = xxx;
    }

}
