package com.example.demo.controller;

import com.example.demo.model.GameModel;
import com.example.demo.model.MessageModel;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api/v2")
public class MessageController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/messages")
    public List<Map<String, Object>> getMessages(@RequestParam String gameId) {
        List<Map<String, Object>> response = new ArrayList<>();
        messageService.getMessagesByGameId(gameId).forEach(messageModel -> response.add(
                messageModel.getMessageMap(gameService.getTurn(gameId))));
        return response;
    }

    @PostMapping("/solve")
    public Map<String, Object> solveMessage(@RequestParam String gameId, @RequestParam String adId) {
        MessageModel messageModel = messageService.getMessageByGameIdAndAdId(gameId, adId);
        GameModel gameModel = gameService.getGameByGameId(gameId);
        return messageService.solveMessage(gameModel, messageModel);
    }


}
