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

    private GenerateMessages generateMessages = new GenerateMessages();

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private HighScoreRepository highScoreRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ReputationRepository reputationRepository;

    public GameModel generateNewGameAndReputation() {
        GameModel gameModel = generateNewGame();
        generateReputation(gameModel.getGameId());
        return gameModel;
    }

    public GameModel generateNewGame() {
        GameModel gameModel = new GameModel();
        gameModel.setLives(3);
        gameModel.setHighScore(getHighScore());
        return saveGame(gameModel);
    }

    private ReputationModel generateReputation(String gameId) {
        ReputationModel reputationModel = new ReputationModel();
        reputationModel.setGameId(gameId);
        return saveReputation(reputationModel);
    }

    public ReputationModel saveReputation(ReputationModel reputationModel) {
        return reputationRepository.save(reputationModel);
    }

    public GameModel saveGame(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }

    public int getHighScore() {
        Optional<HighScoreModel> highScoreModel = highScoreRepository.findById(1L);
        return highScoreModel.map(HighScoreModel::getHighScore).orElse(0);
    }

    public List<MessageModel> getMessagesByGameId(String gameId) {
        List<MessageModel> messageList = new ArrayList<>();
        messageRepository.findAllByGameId(gameId).forEach(messageList::add);
        return messageList;
    }

    public void populateMessages(String gameId) {
        List<MessageModel> messages = generateMessages.getMessages(gameId);
        messages.forEach(this::saveMessage);
    }

    public MessageModel saveMessage(MessageModel messageModel) {
        return messageRepository.save(messageModel);
    }

    public ReputationModel getReputationByGameId(String gameId) {
        return reputationRepository.findByGameId(gameId);
    }

    public long getTurn(String gameId) {
        return gameRepository.findByGameId(gameId).getTurn();
    }

    public MessageModel getMessageByGameIdAndAdId(String gameId, String adId) {
        return messageRepository.findByGameIdAndAdId(gameId, adId);
    }

    public GameModel getGameByGameId(String gameId) {
        return gameRepository.findByGameId(gameId);
    }
}
