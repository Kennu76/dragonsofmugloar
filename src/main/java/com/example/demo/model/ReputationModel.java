package com.example.demo.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class ReputationModel {

    @Id
    @GeneratedValue
    private long id;
    private String gameId;
    private int people = 0;
    private int state = 0;
    private int underworld = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUnderworld() {
        return underworld;
    }

    public void setUnderworld(int underworld) {
        this.underworld = underworld;
    }

    public Map<String, Object> getReputationMap() {
        Map<String, Object> reputationMap = new TreeMap<>();
        reputationMap.put("people", getPeople());
        reputationMap.put("state", getState());
        reputationMap.put("underworld", getUnderworld());
        return reputationMap;
    }
}
