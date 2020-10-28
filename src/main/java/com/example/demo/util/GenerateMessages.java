package com.example.demo.util;

import com.example.demo.model.ItemModel;
import com.example.demo.model.MessageModel;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GenerateMessages {

    public static List<MessageModel> getMessages(String gameId) {
        List<MessageModel> messageModels = new ArrayList<>();
        JSONArray messagesAsJson = readAndParseJson("messages.json");
        messagesAsJson.forEach(message -> messageModels.add(parseMessageObject((JSONObject) message, gameId)));
        return messageModels;
    }

    public static List<ItemModel> getItems(String gameId) {
        List<ItemModel> itemModels = new ArrayList<>();
        JSONArray itemsAsJson = readAndParseJson("items.json");
        itemsAsJson.forEach(item -> itemModels.add(parseItemObject((JSONObject) item, gameId)));
        return itemModels;
    }


    public static JSONArray readAndParseJson(String path) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(new File(path))) {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static MessageModel parseMessageObject(JSONObject message, String gameId) {
        String adId = (String) message.get("adId");
        String messageString = (String) message.get("message");
        long reward = (long) message.get("reward");
        long expiresIn = (long) message.get("expiresIn");
        String encrypted = (String) message.get("encrypted");
        String probability = (String) message.get("probability");
        return new MessageModel(adId, gameId, messageString, reward, expiresIn, encrypted, probability);
    }

    private static ItemModel parseItemObject(JSONObject item, String gameId) {
        String id = (String) item.get("id");
        String name = (String) item.get("name");
        long cost = (long) item.get("cost");
        long additionalLives = (long) item.get("lives");
        long additionalLevels = (long) item.get("level");
        return new ItemModel(id, gameId, name, cost, additionalLives, additionalLevels);
    }

}
