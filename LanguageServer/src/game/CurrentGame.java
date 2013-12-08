package game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.CommonUtils;
import dto.Game;

public abstract class CurrentGame {

    private Game game;
    private Date startTime;
    private Date finishTime;
    private List<List<Integer>> solution = new ArrayList<>();

    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;
        List<Integer> list = solution.get(id);
        if (CommonUtils.isNotNull(list) && list.equals(ids))
        {
            correct = true;
        }
        return correct;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getFinishTime()
    {
        return finishTime;
    }

    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }

    public List<List<Integer>> getSolution()
    {
        return solution;
    }

    public void setSolution(List<List<Integer>> solution)
    {
        this.solution = solution;
    }

}
