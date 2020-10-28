package com.example.demo.service;

import com.example.demo.model.GameModel;
import com.example.demo.model.HighScoreModel;
import com.example.demo.model.MessageModel;
import com.example.demo.model.ReputationModel;
import com.example.demo.repo.HighScoreRepository;
import com.example.demo.repo.MessageRepository;
import com.example.demo.util.GenerateMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    public ReputationService reputationService;

    @Autowired
    public HighScoreService highScoreService;

    @Autowired
    public GameService gameService;


    public List<MessageModel> getMessagesByGameId(String gameId) {
        return messageRepository.findAllByGameId(gameId);
    }

    public void populateMessages(String gameId) {
        List<MessageModel> messages = GenerateMessages.getMessages(gameId);
        messages.forEach(this::saveMessage);
    }

    public void saveMessage(MessageModel messageModel) {
        messageRepository.save(messageModel);
    }


    public MessageModel getMessageByGameIdAndAdId(String gameId, String adId) {
        return messageRepository.findByGameIdAndAdId(gameId, adId);
    }

    Map<String, Integer> difficultyMap = new HashMap<>() {{
        put("Piece of cake", 0);
        put("Walk in the park", 1);
        put("Hmmm....", 2);
        put("Risky", 3);
    }};

    public Map<String, Object> solveMessage(GameModel gameModel, MessageModel messageModel) {
        String message;
        boolean isSuccess = calculateMissionSuccess(gameModel, messageModel);
        if (isSuccess) {
            message = solveSuccessfulMissionAndGetMessage(gameModel, messageModel);
        } else {
            message = solveFailedMissionAndGetMessage(gameModel);
        }
        messageRepository.delete(messageModel);
        return createResponse(isSuccess, gameModel, message);
    }

    private String solveSuccessfulMissionAndGetMessage(GameModel gameModel, MessageModel messageModel) {
        String message = "You succeeded in the mission! Good job you playa!";
        highScoreService.updateHighScore(gameModel.getScore() + messageModel.getReward());
        updateGameValuesForSuccess(gameModel, messageModel.getReward());
        resolveReputationChangeWhenMissionSuccess(gameModel, getDifficultyLevel(messageModel));
        gameService.saveGame(gameModel);
        return message;
    }

    private String solveFailedMissionAndGetMessage(GameModel gameModel) {
        String message;
        updateGameValuesForFailure(gameModel);
        boolean isGameOver = gameModel.getLives() == 0;
        if (isGameOver) {
            message = "That was your last life! GAME OVER!!!";
            gameService.deleteGame(gameModel);
        } else {
            message = "You failed at the mission!";
            gameService.saveGame(gameModel);
        }
        return message;
    }

    public boolean calculateMissionSuccess(GameModel gameModel, MessageModel messageModel) {
        int difficultyLevel = getDifficultyLevel(messageModel);
        return difficultyLevel <= gameModel.getLevel();
    }

    public Map<String, Object> createResponse(boolean isSuccess, GameModel gameModel, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", isSuccess);
        response.putAll(gameModel.getMapForAction(highScoreService.getHighScore(gameModel.getScore())));
        response.put("message", message);
        return response;
    }

    public int getDifficultyLevel(MessageModel messageModel) {
        return difficultyMap.get(messageModel.getProbability());
    }

    private void updateGameValuesForFailure(GameModel gameModel) {
        gameModel.setLives(gameModel.getLives() - 1);
        gameModel.setTurn(gameModel.getTurn() + 1);
    }

    private void updateGameValuesForSuccess(GameModel gameModel, long reward) {
        gameModel.setGold(gameModel.getGold() + reward);
        gameModel.setScore(gameModel.getScore() + reward);
        gameModel.setHighScore(highScoreService.getHighScore());
        gameModel.setTurn(gameModel.getTurn() + 1);
    }

    public void resolveReputationChangeWhenMissionSuccess(GameModel gameModel, int difficultyLevel) {
        ReputationModel reputationModel = new ReputationModel();
        reputationModel.setGameId(gameModel.getGameId());
        reputationModel.setPeople(calculateReward(difficultyLevel));
        reputationModel.setState(calculateReward(difficultyLevel));
        reputationModel.setUnderworld(calculateReward(difficultyLevel));
        reputationService.saveReputation(reputationModel);
    }

    public long calculateReward(int difficultyLevel) {
        return (long) (Math.random() * (long) difficultyLevel);
    }

}
