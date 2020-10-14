package com.example.demo.service;

import com.example.demo.model.GameModel;
import com.example.demo.model.HighScoreModel;
import com.example.demo.model.MessageModel;
import com.example.demo.repo.GameRepository;
import com.example.demo.repo.HighScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class ActionService {

    @Autowired
    public GameRepository gameRepository;

    @Autowired
    public HighScoreRepository highScoreRepository;

    Map<String, Integer> difficultyMap = new HashMap<>() {{
        put("Piece of cake", 0);
        put("Walk in the park", 1);
        put("Hmmm....", 2);
        put("Risky", 3);
    }};

    public Map<String, Object> getResponseForAction(GameModel gameModel, MessageModel messageModel) {
        String message;
        boolean isSuccess = calculateMissionSuccess(gameModel, messageModel);
        if (isSuccess) {
            updateGameValuesForSuccess(gameModel, messageModel.getReward());
            message = "You succeeded in the mission! Good job you playa!";
            gameRepository.save(gameModel);
        } else {
            updateGameValuesForFailure(gameModel);
            boolean isGameOver = gameModel.getLives() == 0;
            if (isGameOver) {
                message = "That was your last life! GAME OVER!!!";
                gameRepository.delete(gameModel);
            } else {
                message = "You failed at the mission!";
                gameRepository.save(gameModel);
            }
        }

        Map<String, Object> response = new TreeMap<>();
        response.put("success", isSuccess);
        response.putAll(gameModel.getMapForAction(getHighScore()));
        response.put("message", message);
        return response;
    }

    public int getHighScore() {
        Optional<HighScoreModel> highScoreModel = highScoreRepository.findById(1L);
        return highScoreModel.map(HighScoreModel::getHighScore).orElse(0);
    }

    private void updateGameValuesForFailure(GameModel gameModel) {
        gameModel.setLives(gameModel.getLives() - 1);
        gameModel.setTurn(gameModel.getTurn() + 1);
    }

    private void updateGameValuesForSuccess(GameModel gameModel, long reward) {
        gameModel.setGold(gameModel.getGold() + reward);
        gameModel.setScore(gameModel.getScore() + reward);
        gameModel.setTurn(gameModel.getTurn() + 1);
    }

    public boolean calculateMissionSuccess(GameModel gameModel, MessageModel messageModel) {
        int difficultyLevel = difficultyMap.get(messageModel.getProbability());
        return difficultyLevel <= gameModel.getLevel();
    }
}
