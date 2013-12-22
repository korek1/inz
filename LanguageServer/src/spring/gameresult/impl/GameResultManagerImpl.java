package spring.gameresult.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.gameresult.GameResultDAO;
import spring.gameresult.GameResultManager;
import spring.student.StudentDAO;
import dto.GameResult;
import dto.to.TOsManager;
import dto.to.gameresult.GameResultTOs;

@Service
public class GameResultManagerImpl implements GameResultManager {

    @Autowired
    private GameResultDAO gameResultDAO;
    
    
    @Override
    @Transactional
    public void saveOrUpdateGameResult(GameResult gameResult, String login)
    {
        gameResultDAO.saveOrUpdateGameResult(gameResult, login);
    }


    @Override
    @Transactional
    public GameResultTOs getStudentsGamesResult(int studentID)
    {
        List<GameResult> studentsGamesResults = gameResultDAO.getStudentsGamesResults(studentID);
        
        System.out.println("startuje convert");
        GameResultTOs convertGameResultTO = TOsManager.convertGameResultTO(studentsGamesResults);
        
        return convertGameResultTO;
    }

}
