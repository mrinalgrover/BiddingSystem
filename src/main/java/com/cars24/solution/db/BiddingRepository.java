package com.cars24.solution.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BiddingRepository extends CrudRepository<BiddingSystem,Integer> {

  List<BiddingSystem> findAll();

  BiddingSystem findById(long id);
}