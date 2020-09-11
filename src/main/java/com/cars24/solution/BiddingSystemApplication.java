package com.cars24.solution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cars24.solution.model.BiddingSystem;
import com.cars24.solution.repository.BiddingRepository;

@SpringBootApplication
public class BiddingSystemApplication {

	private static final Logger log = LoggerFactory.getLogger(BiddingSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BiddingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BiddingRepository repository) {
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

}
