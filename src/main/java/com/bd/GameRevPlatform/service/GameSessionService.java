package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GameSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Timofti Gabriel
 */

@Service
public class GameSessionService {
    private GameSessionDao gameSessionDao;

    @Autowired
    public GameSessionService(GameSessionDao gameSessionDao) {
        this.gameSessionDao = gameSessionDao;
    }

    public void deleteGameSession(int game_id){
        gameSessionDao.deleteGameSession(game_id);
    }
}
