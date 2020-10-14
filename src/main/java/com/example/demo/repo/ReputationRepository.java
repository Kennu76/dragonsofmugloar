package com.example.demo.repo;

import com.example.demo.model.GameModel;
import com.example.demo.model.ReputationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputationRepository extends CrudRepository<ReputationModel, Long> {

    ReputationModel findByGameId(String gameId);
}
