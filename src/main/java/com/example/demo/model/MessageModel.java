package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class MessageModel {


    @Id
    private String adId;
    private String gameId;
    private String message;
    private long reward;
    private long expiresIn;
    private  String encrypted;
    private String probability;

    public MessageModel() {

    }

    public MessageModel(String adId, String gameid, String message, long reward, long expiresIn, String encrypted, String probability) {
        this.adId = adId;
        this.gameId = gameid;
        this.message = message;
        this.reward = reward;
        this.expiresIn = expiresIn;
        this.encrypted = encrypted;
        this.probability = probability;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public Map<String, Object> getMessageMap(long turn) {
        Map<String, Object> messageMap = new TreeMap<>();
        messageMap.put("adId", getAdId());
        messageMap.put("message", getMessage());
        messageMap.put("reward", getReward());
        messageMap.put("expiresIn", getExpiresIn() - turn);
        messageMap.put("encrypted", getEncrypted());
        messageMap.put("probability", getProbability());
        return messageMap;
    }
}
