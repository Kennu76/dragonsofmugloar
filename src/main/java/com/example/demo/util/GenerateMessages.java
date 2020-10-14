package com.example.demo.util;

import com.example.demo.model.MessageModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateMessages {

    private List<MessageModel> messages = new ArrayList<>();
    private String gameId;

    public List<MessageModel> getMessages(String gameId) {
        this.gameId = gameId;
        readJson();
        return messages;
    }
    public void readJson() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(new File("messages.json"))) {
            Object obj = jsonParser.parse(reader);
            JSONArray messageList = (JSONArray) obj;
            messageList.forEach(message -> messages.add(parseMessageObject((JSONObject) message)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private MessageModel parseMessageObject(JSONObject message) {
        String adId = (String) message.get("adId");
        String messageString = (String) message.get("message");
        long reward = (long) message.get("reward");
        long expiresIn = (long) message.get("expiresIn");
        String encrypted = (String) message.get("encrypted");
        String probability = (String) message.get("probability");
        return new MessageModel(adId, gameId, messageString, reward, expiresIn, encrypted, probability);
    }

}
