package com.balance.gym.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.balance.gym.model.Excercise;

public interface  ExcerciseRepository extends MongoRepository<Excercise, String> {

    public List<Excercise> findByName(String firstName);


}