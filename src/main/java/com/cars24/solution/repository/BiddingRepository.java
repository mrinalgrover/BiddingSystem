package com.cars24.solution.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cars24.solution.model.BiddingSystem;

public interface BiddingRepository extends PagingAndSortingRepository<BiddingSystem,Integer> {


  BiddingSystem findById(int id);
  
}