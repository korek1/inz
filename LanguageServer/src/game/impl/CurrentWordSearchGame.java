package game.impl;

import game.CurrentGame;

import java.util.ArrayList;
import java.util.List;

import utils.CommonUtils;

public class CurrentWordSearchGame extends CurrentGame {

    private List<String> wordsSolution = new ArrayList<>();
    private String wordToCheck;

    public CurrentWordSearchGame()
    {
        super();
    }
    
    

    @Override
    public boolean checkIfPartOfGameIsCorrect(int id, List<Integer> ids)
    {
        boolean correct = false;
        if (CommonUtils.isNotNull(wordsSolution) && wordsSolution.contains(wordToCheck))
        {
            correct = true;
        }
        return correct;
    }



    public List<String> getWordsSolution()
    {
        return wordsSolution;
    }

    public void setWordsSolution(List<String> wordsSolution)
    {
        this.wordsSolution = wordsSolution;
    }


    public String getWordToCheck()
    {
        return wordToCheck;
    }


    public void setWordToCheck(String wordToCheck)
    {
        this.wordToCheck = wordToCheck;
    }
    

    
    
    

}
