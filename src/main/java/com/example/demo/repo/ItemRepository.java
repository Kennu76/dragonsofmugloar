package com.example.demo.repo;

import com.example.demo.model.GameModel;
import com.example.demo.model.ItemModel;
import com.example.demo.model.MessageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemRepository extends CrudRepository<ItemModel, Long> {

    List<ItemModel> findAllByGameId(String gameId);

    ItemModel findByGameIdAndId(String gameId, String id);
}

