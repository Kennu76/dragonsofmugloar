package com.example.demo.repo;

import com.example.demo.model.HighScoreModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighScoreRepository extends CrudRepository<HighScoreModel, Long> {
}
