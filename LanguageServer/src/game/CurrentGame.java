package game;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.CommonUtils;
import dto.Game;

public abstract class CurrentGame {

    private Game game;
    private Date startTime;
    private Date finishTime;
    private List<List<Integer>> solution = new ArrayList<>();

    private int noumberOfTasks;
    private int attempts;
    private int corectAnswers;

    private Set<Integer> attempted = new HashSet<>();

    public CurrentGame(Game game)
    {
        super();
        this.game = game;
    }

    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;

        boolean canPerformChecking = manageAttempt(id);
        if (canPerformChecking)
        {
            increaseAttempts();

            List<Integer> list = solution.get(id);
            if (CommonUtils.isNotNull(list) && list.equals(ids))
            {
                correct = true;
                increaseCorectAnswers();
            }

        }
        return correct;
    }

    public long getGameDuration()
    {
        return CommonUtils.differenceInSecond(startTime, finishTime);
    }

    public boolean manageAttempt(Integer i)
    {
        boolean canPerformChecking = false;

        if (i >= 0 && i < noumberOfTasks && attempted.add(i))
        {
            canPerformChecking = true;
        }

        return canPerformChecking;
    }

    public void increaseAttempts()
    {
        attempts++;
    }

    public void increaseCorectAnswers()
    {
        corectAnswers++;
    }

    public boolean isGameFinished()
    {
        return attempts == noumberOfTasks ? true : false;
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
        this.noumberOfTasks = solution.size();
    }

    public int getNoumberOfTasks()
    {
        return noumberOfTasks;
    }

    public void setNoumberOfTasks(int noumberOfTasks)
    {
        this.noumberOfTasks = noumberOfTasks;
    }

    public int getAttempts()
    {
        return attempts;
    }

    public void setAttempts(int attempts)
    {
        this.attempts = attempts;
    }

    public int getCorectAnswers()
    {
        return corectAnswers;
    }

    public void setCorectAnswers(int corectAnswers)
    {
        this.corectAnswers = corectAnswers;
    }
    
    protected void removeFromAttempted(Integer i)
    {
        attempted.remove(i);
    }

}
