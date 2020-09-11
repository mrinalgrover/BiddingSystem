package com.cars24.solution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cars24.solution.model.BiddingSystem;
import com.cars24.solution.model.UserStore;
import com.cars24.solution.repository.BiddingRepository;
import com.cars24.solution.repository.UserRepository;

@SpringBootApplication
public class BiddingSystemApplication {

	private static final Logger log = LoggerFactory.getLogger(BiddingSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BiddingSystemApplication.class, args);
	}

	//Generate 100 random auction items
	
	@Bean
	public CommandLineRunner createAuction(BiddingRepository repository) {
		return (args) -> {
			// Initialise auction
			
			Random rand = new Random(); 
			
			for(int i=0; i<100; i++)
				repository.save(new BiddingSystem((rand.nextInt(10)+1)*100,100));
			
			for (BiddingSystem bid : repository.findAll()) {
				log.info(bid.toString());
			}


		};
	}
	

	//Generate 2 users
	

	@Bean
	public CommandLineRunner createUsers(UserRepository repository) {
		return (args) -> {
			// Initialise auction
			
			repository.save(new UserStore(1, "abc"));
			repository.save(new UserStore(2, "xyz"));
			for (UserStore user : repository.findAll()) {
				log.info(user.toString());
			}


		};
	}



}
