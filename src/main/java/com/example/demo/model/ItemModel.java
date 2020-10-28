package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class ItemModel {

    @Id
    public String id;
    public String gameId;
    public String name;
    public long cost;

    public long getAdditionalLives() {
        return additionalLives;
    }

    public void setAdditionalLives(long additionalLives) {
        this.additionalLives = additionalLives;
    }

    public long getAdditionalLevels() {
        return additionalLevels;
    }

    public void setAdditionalLevels(long additionalLevels) {
        this.additionalLevels = additionalLevels;
    }

    public long additionalLives;
    public long additionalLevels;

    public ItemModel(String id, String gameId, String name, long cost, long additionalLives, long additionalLevels) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.cost = cost;
        this.additionalLives = additionalLives;
        this.additionalLevels = additionalLevels;
    }

    public ItemModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Map<String, Object> getItemMap() {
        Map<String, Object> messageMap = new LinkedHashMap<>();
        messageMap.put("id", getId());
        messageMap.put("name", getName());
        messageMap.put("cost", getCost());
        return messageMap;
    }
}
