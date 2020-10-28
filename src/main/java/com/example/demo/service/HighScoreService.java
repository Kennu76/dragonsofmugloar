package com.example.demo.service;

import com.example.demo.model.HighScoreModel;
import com.example.demo.model.ReputationModel;
import com.example.demo.repo.HighScoreRepository;
import com.example.demo.repo.ReputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HighScoreService {

    @Autowired
    HighScoreRepository highScoreRepository;

    public List<HighScoreModel> getAll() {
        return highScoreRepository.findAll();
    }

    public int getHighScore() {
        return getHighScore(0L);
    }

    public int getHighScore(long newScore) {
        Optional<HighScoreModel> highScoreModel = getHighScoreById(1L);
        return highScoreModel.map(HighScoreModel::getHighScore).orElse((int) newScore);
    }

    public Optional<HighScoreModel> getHighScoreById(Long id) {
        return highScoreRepository.findById(id);
    }

    public void updateHighScore(long newScore) {
        if (newScore > getHighScore()) {
            highScoreRepository.save(new HighScoreModel(1L, (int) newScore));
        }
    }
}

