package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Entity
public class GameModel {

    @Id
    private String gameId = UUID.randomUUID().toString().substring(0, 7);
    private long lives = 0;
    private long gold = 0;
    private long level = 0;
    private long score = 0;
    private long highScore;
    private long turn = 0;

    public GameModel() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public long getLives() {
        return lives;
    }

    public void setLives(long lives) {
        this.lives = lives;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getHighScore() {
        return highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }

    public long getTurn() {
        return turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public Map<String, Object> getMapForAction(long highScore) {
        Map<String, Object> gameMap = new LinkedHashMap<>();
        gameMap.put("lives", getLives());
        gameMap.put("gold", getGold());
        gameMap.put("score", getScore());
        gameMap.put("highScore", highScore);
        gameMap.put("turn", getTurn());
        return gameMap;
    }
}
