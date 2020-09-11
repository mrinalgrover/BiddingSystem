package com.cars24.solution.db;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BiddingRepository extends PagingAndSortingRepository<BiddingSystem,Integer> {


  BiddingSystem findById(int id);
  
}