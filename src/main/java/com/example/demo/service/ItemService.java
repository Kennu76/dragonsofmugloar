package com.example.demo.service;

import com.example.demo.model.GameModel;
import com.example.demo.model.ItemModel;
import com.example.demo.repo.ItemRepository;
import com.example.demo.util.GenerateMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    GameService gameService;

    public List<ItemModel> getItemsByGameId(String gameId) {
        return itemRepository.findAllByGameId(gameId);
    }

    public void populateItems(String gameId) {
        List<ItemModel> items = GenerateMessages.getItems(gameId);
        items.forEach(this::saveItem);
    }

    public void saveItem(ItemModel itemModel) {
        itemRepository.save(itemModel);
    }

    public ItemModel getItemByGameIdAndItemId(String gameId, String itemId) {
        return itemRepository.findByGameIdAndId(gameId, itemId);
    }

    public Map<String, Object> solveItemBuying(String gameId, String itemId) {
        ItemModel itemModel = getItemByGameIdAndItemId(gameId, itemId);
        GameModel gameModel = gameService.getGameByGameId(gameId);
        boolean gameHasEnoughGold = gameModel.getGold() >= itemModel.getCost();
        if (gameHasEnoughGold) {
            changeGameAfterSuccessfulItemBuying(gameModel, itemModel);
            deleteItemOnceBought(itemModel);
        } else {
            changeGameAfterFailingItemBuying(gameModel, itemModel);
        }
        gameService.saveGame(gameModel);
        return createResponse(gameHasEnoughGold, gameModel);

    }

    private void changeGameAfterSuccessfulItemBuying(GameModel gameModel, ItemModel itemModel) {
        gameModel.setLives(gameModel.getLives() + itemModel.getAdditionalLives());
        gameModel.setLevel(gameModel.getLevel() + itemModel.getAdditionalLevels());
        gameModel.setGold(gameModel.getGold() - itemModel.getCost());
        gameModel.setTurn(gameModel.getTurn() + 1);
    }


    private void changeGameAfterFailingItemBuying(GameModel gameModel, ItemModel itemModel) {
        gameModel.setTurn(gameModel.getTurn() + 1);
    }

    public Map<String, Object> createResponse(boolean isSuccess, GameModel gameModel) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("shoppingSuccess", isSuccess);
        response.putAll(gameModel.getMapForItemBuying());
        return response;
    }

    public void deleteItemOnceBought(ItemModel itemModel) {
        itemRepository.delete(itemModel);
    }
}
