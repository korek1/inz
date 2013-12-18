package spring.gameresult.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.gameresult.GameResultDAO;
import spring.gameresult.GameResultManager;
import spring.student.StudentDAO;
import dto.GameResult;

@Service
public class GameResultManagerImpl implements GameResultManager {

    @Autowired
    private GameResultDAO gameResultDAO;
    
    
    @Override
    @Transactional
    public void saveOrUpdateGameResult(GameResult gameResult, String login)
    {
        gameResultDAO.saveOrUpdateGameResult(gameResult, login);
        // TODO Auto-generated method stub
        
    }

}
