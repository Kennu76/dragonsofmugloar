package com.example.demo.service;

import com.example.demo.model.GameModel;
import com.example.demo.model.HighScoreModel;
import com.example.demo.model.ReputationModel;
import com.example.demo.repo.GameRepository;
import com.example.demo.repo.HighScoreRepository;
import com.example.demo.repo.ReputationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class GameServiceTest {

    @Mock
    public GameRepository gameRepository;

    @Mock
    public HighScoreRepository highScoreRepository;

    @Mock
    public ReputationRepository reputationRepository;

    @InjectMocks
    @Spy
    public GameService gameService;

    @InjectMocks
    @Spy
    public HighScoreService highScoreService;

    @InjectMocks
    @Spy
    public ReputationService reputationService;

    @Captor
    ArgumentCaptor<GameModel> gameCaptor;

    @Captor
    ArgumentCaptor<ReputationModel> reputationCaptor;

    @Test
    public void testGenerateGame() {
        when(gameRepository.save(any(GameModel.class))).thenReturn(new GameModel());
        gameService.generateNewGame();

        verify(gameService).saveGame(gameCaptor.capture());
        verify(gameService, times(1)).saveGame(any(GameModel.class));
        GameModel capturedGame = gameCaptor.getValue();

        assertEquals("Amount of lives isnt true", capturedGame.getLives(), 3L);

    }

    @Test
    public void testGetHighScore() {
        HighScoreModel highScoreModel = new HighScoreModel();
        highScoreModel.setHighScore(5);

        // Adding elements to the linked list
        when(highScoreRepository.findById(1L)).thenReturn(java.util.Optional.of(highScoreModel));
        assertEquals("Highscore incorrect", highScoreService.getHighScore(), 5);
    }

    @Test
    public void testGetHighScoreMissing() {
        HighScoreModel highScoreModel = new HighScoreModel();
        highScoreModel.setHighScore(5);

        // Adding elements to the linked list
        when(highScoreRepository.findById(1L)).thenReturn(Optional.empty());
        assertEquals("Highscore incorrect", highScoreService.getHighScore(), 0);
    }

    @Test
    public void testReputationCreation() {
        GameModel gameModel = new GameModel();
        gameModel.setGameId("1");

        when(gameRepository.save(any(GameModel.class))).thenReturn(gameModel);
        when(reputationRepository.save(any(ReputationModel.class))).thenReturn(new ReputationModel());

        gameService.generateNewGameAndReputation();

        verify(reputationService).saveReputation(reputationCaptor.capture());
        verify(reputationService, times(1)).saveReputation(any(ReputationModel.class));
        ReputationModel capturedRep = reputationCaptor.getValue();

        assertEquals("Reputation gameId false", capturedRep.getGameId(), "1");

    }
}
