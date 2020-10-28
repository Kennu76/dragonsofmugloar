package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repo.GameRepository;
import com.example.demo.repo.HighScoreRepository;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.ReputationRepository;
import com.example.demo.util.GenerateMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private HighScoreService highScoreService;

    @Autowired
    private ReputationService reputationService;

    public GameModel generateNewGameAndReputation() {
        GameModel gameModel = generateNewGame();
        reputationService.generateReputation(gameModel.getGameId());
        return gameModel;
    }

    public GameModel generateNewGame() {
        GameModel gameModel = new GameModel();
        gameModel.setLives(3);
        gameModel.setHighScore(highScoreService.getHighScore());
        return saveGame(gameModel);
    }

    public GameModel saveGame(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }

    public void deleteGame(GameModel gameModel) {
        gameRepository.delete(gameModel);
    }

    public long getTurn(String gameId) {
        return gameRepository.findByGameId(gameId).getTurn();
    }

    public GameModel getGameByGameId(String gameId) {
        return gameRepository.findByGameId(gameId);
    }
}
