package com.example.demo.service;

import com.example.demo.model.GameModel;
import com.example.demo.model.MessageModel;
import com.example.demo.model.ReputationModel;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.ReputationRepository;
import com.example.demo.util.GenerateMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    ReputationRepository reputationRepository;


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

    public void resolveReputationChangeWhenMissionSuccess(GameModel gameModel, int difficultyLevel) {
        ReputationModel reputationModel = new ReputationModel();
        reputationModel.setGameId(gameModel.getGameId());
        reputationModel.setPeople(calculateReward(difficultyLevel));
        reputationModel.setState(calculateReward(difficultyLevel));
        reputationModel.setUnderworld(calculateReward(difficultyLevel));
        reputationRepository.save(reputationModel);
    }

    public long calculateReward(int difficultyLevel) {
        return (long) (Math.random() * (long) difficultyLevel);
    }


}
