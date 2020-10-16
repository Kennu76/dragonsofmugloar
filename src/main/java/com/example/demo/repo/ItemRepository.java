package com.example.demo.repo;

import com.example.demo.model.GameModel;
import com.example.demo.model.ItemModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemModel, Long> {

}
