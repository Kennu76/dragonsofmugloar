package com.example.demo.controller;

import com.example.demo.model.GameModel;

import com.example.demo.model.MessageModel;
import com.example.demo.service.ActionService;
import com.example.demo.service.GameService;
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
    private ActionService actionService;

    @PostMapping("/game/start")
    public GameModel startGame() {
        GameModel gameModel = gameService.generateNewGameAndReputation();
        gameService.populateMessages(gameModel.getGameId());
        gameModel.setHighScore(gameService.getHighScore());
        return gameModel;
    }

    @PostMapping("/investigate/reputation")
    public Map<String, Object> investigateReputation(@RequestParam String gameId) {
        return gameService.getReputationByGameId(gameId).getReputationMap();
    }

    @GetMapping("/messages")
    public List<Map<String, Object>> getMessages(@RequestParam String gameId) {
        List<Map<String, Object>> response = new ArrayList<>();
        gameService.getMessagesByGameId(gameId).forEach(messageModel -> response.add(
                messageModel.getMessageMap(gameService.getTurn(gameId))));
        return response;
    }

    @PostMapping("/solve")
    public Map<String, Object> solveMessage(@RequestParam String gameId,
                                                  @RequestParam String adId) {

        MessageModel messageModel = gameService.getMessageByGameIdAndAdId(gameId, adId);
        GameModel gameModel = gameService.getGameByGameId(gameId);
        return actionService.getResponseForAction(gameModel, messageModel);
    }


}