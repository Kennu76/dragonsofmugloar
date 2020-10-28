package com.example.demo.repo;

import com.example.demo.model.HighScoreModel;
import com.example.demo.model.MessageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighScoreRepository extends CrudRepository<HighScoreModel, Long> {

    List<HighScoreModel> findAll();
}
