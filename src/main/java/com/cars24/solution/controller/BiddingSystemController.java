package com.cars24.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cars24.solution.config.BiddingConfiguration;
import com.cars24.solution.model.BiddingSystem;
import com.cars24.solution.repository.BiddingRepository;
import com.cars24.solution.service.AuthenticatorService;
import com.cars24.solution.service.BidValidator;
import com.cars24.solution.utility.BiddingStatus;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BiddingSystemController {

	private static final Logger log = LoggerFactory.getLogger(BiddingSystemController.class);

	@Autowired
	BiddingRepository biddingRepository;
	
	@Autowired
	BidValidator bidValidator;

	@Autowired
	AuthenticatorService authenticator;
	
	@Autowired
	BiddingConfiguration config;

	@GetMapping("/auction")
	public @ResponseBody Page<BiddingSystem> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {

		return biddingRepository.findAll(pageable);

	}

	@PostMapping("/auction/{itemCode}/bid")
	public ResponseEntity<String> placeNewBid(@RequestHeader Map<String, String> headers, @PathVariable int itemCode,
			@RequestParam int bidAmount) {

		
		
		log.info("Bidding for item - " + itemCode + ", amount : " + bidAmount);
		
		int userId = headers.get("userid")!= null ? Integer.parseInt(headers.get("userid")) : 0;
		
		if(authenticator.isUserLoggedIn(userId, headers.get("usertoken")))
			log.info(userId + " placed the bid and is logged in");
		
		else {
			return ResponseEntity.status(401).body("UnAuthorized, Send Valid UserId & UserToken");
		}
		
		BiddingStatus status = bidValidator.isValidBid(itemCode, bidAmount);
		
		switch (status) {
		
		case BID_ACCEPTED: 
			return ResponseEntity.status(201).body("Bid is Accepted");
			
		case BID_REJECTED: 
			return ResponseEntity.status(406).body("Bid is Rejected");
			
		case AUCTION_DOESNT_EXIST: 
			return ResponseEntity.status(404).body("Auction not found");

		case BID_CONFLICT:
			// A "Politically correct" way to handle conflicts in Restful apis
			return ResponseEntity.status(409).body("Conflict, Try again");

		default:
			return ResponseEntity.status(501).body("Internal Server error");
		}
		
		
	}

}
