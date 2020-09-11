package com.cars24.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cars24.solution.db.BiddingRepository;
import com.cars24.solution.db.BiddingSystem;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BiddingSystemController {

	private static final Logger log = LoggerFactory.getLogger(BiddingSystemController.class);

	@Autowired
	BiddingRepository biddingRepository;

	/*
	 * GET /auction?status=RUNNING
Response should contain the following attributes
1. Item Code
2. Highest bid amount placed
3. Step Rate
API should be paginated in nature. And you are free to change API structure based on your design
	 */
	@GetMapping("/auction")
	public @ResponseBody Page<BiddingSystem> index(@PageableDefault(page = 0, size = 20)
	Pageable pageable){
		
		return  biddingRepository.findAll(pageable);
		
	}



	@PostMapping("/auction/{itemCode}/bid") 
	public ResponseEntity<String>placeNewBid ( @PathVariable int itemCode, @RequestParam int bidAmount) {

		
		log.info("Bidding for item - "+itemCode + ", amount : "+bidAmount);
		
		BiddingSystem bs = biddingRepository.findById(itemCode);
		
		if(bs!= null && bs.getId() == itemCode) {
			
			
			int validBid = Math.max(bs.getMinimumBasePrice(), bs.getHighestBidPlaced() + bs.getStepRate());
			
			if(bidAmount >= validBid) {
				bs.setHighestBidPlaced(bidAmount);
				biddingRepository.save(bs);
				return ResponseEntity.status(201).body("Bid is Accepted");
				
			}
			
			else
				return ResponseEntity.status(406).body("Bid is Rejected");
		}
		else
			return ResponseEntity.status(404).body("Auction not found");

		
		
	}
	
}
