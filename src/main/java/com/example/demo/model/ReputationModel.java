package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class ReputationModel {

    @Id
    @GeneratedValue
    private long id;
    private String gameId;
    private long people = 0;
    private long state = 0;
    private long underworld = 0;

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

    public long getPeople() {
        return people;
    }

    public void setPeople(long people) {
        this.people = people;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getUnderworld() {
        return underworld;
    }

    public void setUnderworld(long underworld) {
        this.underworld = underworld;
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
