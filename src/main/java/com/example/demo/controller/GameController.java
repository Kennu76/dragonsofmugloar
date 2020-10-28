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
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private ReputationService reputationService;

    @PostMapping("/game/start")
    public GameModel startGame() {
        GameModel gameModel = gameService.generateNewGameAndReputation();
        messageService.populateMessages(gameModel.getGameId());
        itemService.populateItems(gameModel.getGameId());
        gameModel.setHighScore(gameService.getHighScore());
        return gameModel;
    }

    @GetMapping("/game/status")
    public GameModel gameStatus(@RequestParam String gameId) {
        return gameService.getGameByGameId(gameId);
    }

    @PostMapping("/investigate/reputation")
    public Map<String, Object> investigateReputation(@RequestParam String gameId) {
        return reputationService.getReputationByGameId(gameId).getReputationMap();
    }

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
        return actionService.solveMessage(gameModel, messageModel);
    }


}
