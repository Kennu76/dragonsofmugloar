package com.example.demo.service;

import com.example.demo.model.ReputationModel;
import com.example.demo.repo.ReputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Service
public class ReputationService {

    @Autowired
    ReputationRepository reputationRepository;

    protected ReputationModel generateReputation(String gameId) {
        ReputationModel reputationModel = new ReputationModel();
        reputationModel.setGameId(gameId);
        return saveReputation(reputationModel);
    }

    public ReputationModel saveReputation(ReputationModel reputationModel) {
        return reputationRepository.save(reputationModel);
    }


    public ReputationModel getReputationByGameId(String gameId) {
        return reputationRepository.findByGameId(gameId);
    }
}
