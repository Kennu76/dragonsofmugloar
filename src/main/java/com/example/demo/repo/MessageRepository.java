package com.example.demo.repo;

import com.example.demo.model.HighScoreModel;
import com.example.demo.model.MessageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageModel, Long> {

    List<MessageModel> findAllByGameId(String gameId);

    MessageModel findByGameIdAndAdId(String gameId, String adId);

    MessageModel deleteByAdId(String adId);
}
