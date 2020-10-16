package com.example.demo.service;


import com.example.demo.model.GameModel;
import com.example.demo.model.HighScoreModel;
import com.example.demo.model.MessageModel;
import com.example.demo.model.ReputationModel;
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
    private HighScoreRepository highScoreRepository;

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
        gameModel.setHighScore(getHighScore());
        return saveGame(gameModel);
    }

    public GameModel saveGame(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }

    public int getHighScore() {
        Optional<HighScoreModel> highScoreModel = highScoreRepository.findById(1L);
        return highScoreModel.map(HighScoreModel::getHighScore).orElse(0);
    }

    public long getTurn(String gameId) {
        return gameRepository.findByGameId(gameId).getTurn();
    }

    public GameModel getGameByGameId(String gameId) {
        return gameRepository.findByGameId(gameId);
    }
}
