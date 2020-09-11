package com.cars24.solution.repository;



import org.springframework.data.repository.CrudRepository;
import com.cars24.solution.model.UserStore;

public interface UserRepository extends CrudRepository<UserStore,Integer> {


	UserStore findById(int id);
  
}

