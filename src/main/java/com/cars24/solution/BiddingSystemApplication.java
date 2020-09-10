package com.cars24.solution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cars24.solution.db.BiddingRepository;
import com.cars24.solution.db.BiddingSystem;

@SpringBootApplication
public class BiddingSystemApplication {

	private static final Logger log = LoggerFactory.getLogger(BiddingSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BiddingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BiddingRepository repository) {
		return (args) -> {
			// initialize auction

			for(int i=0; i<25; i++)
				repository.save(new BiddingSystem(100,10));

			for (BiddingSystem bid : repository.findAll()) {
				log.info(bid.toString());
			}
			log.info("");

		};
	}

}
