package com.example.demo.repo;

import com.example.demo.model.GameModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameModel, Long> {

    GameModel findByGameId(String gameId);
}
