package com.cars24.solution.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BiddingRepository extends PagingAndSortingRepository<BiddingSystem,Integer> {

 // List<BiddingSystem> findAll();

  BiddingSystem findById(int id);
}