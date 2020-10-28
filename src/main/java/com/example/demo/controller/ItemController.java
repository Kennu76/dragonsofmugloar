package com.example.demo.controller;

import com.example.demo.model.GameModel;
import com.example.demo.model.ItemModel;
import com.example.demo.model.MessageModel;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api/v2")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/shop")
    public List<Map<String, Object>> getShopItems(@RequestParam String gameId) {
        List<Map<String, Object>> response = new ArrayList<>();
        itemService.getItemsByGameId(gameId).forEach(itemModel -> response.add(itemModel.getItemMap()));
        return response;
    }

    @PostMapping("/shop/buy")
    public Map<String, Object> buyShopItems(@RequestParam String gameId, @RequestParam String itemId) {
        return itemService.solveItemBuying(gameId, itemId);
    }

}
