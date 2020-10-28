package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HighScoreModel {

    @Id
    private long id;

    private int highScore;

    public HighScoreModel() {
    }

    public HighScoreModel(long id, int highScore) {
        this.id = id;
        this.highScore = highScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
