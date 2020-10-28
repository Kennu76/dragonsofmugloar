package com.example.demo.controller;

import com.example.demo.model.GameModel;

import com.example.demo.model.HighScoreModel;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api/v2")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private HighScoreService highScoreService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ReputationService reputationService;

    @PostMapping("/game/start")
    public Map<String, Object> startGame() {
        GameModel gameModel = gameService.generateNewGameAndReputation();
        messageService.populateMessages(gameModel.getGameId());
        itemService.populateItems(gameModel.getGameId());
        gameModel.setHighScore(highScoreService.getHighScore());
        return gameModel.getMap();
    }

    @GetMapping("/game/status")
    public Map<String, Object> gameStatus(@RequestParam String gameId) {
        return gameService.getGameByGameId(gameId).getMap();
    }

    @PostMapping("/investigate/reputation")
    public Map<String, Object> investigateReputation(@RequestParam String gameId) {
        return reputationService.getReputationByGameId(gameId).getReputationMap();
    }


    @GetMapping("/highScores")
    public List<HighScoreModel> getMessages() {
        return highScoreService.getAll();
    }



}
