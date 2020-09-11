package com.cars24.solution.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import com.cars24.solution.model.BiddingSystem;
import com.cars24.solution.repository.BiddingRepository;
import com.cars24.solution.utility.BiddingStatus;

@Component
public class BidValidator {

	private static final Logger log = LoggerFactory.getLogger(BidValidator.class);

	@Autowired
	BiddingRepository biddingRepository;
	
	public BiddingStatus isValidBid(int itemCode, int bidAmount)
	{
		try {
			BiddingSystem bs = biddingRepository.findById(itemCode);

			if (bs != null && bs.getId() == itemCode) {

				int validBid = Math.max(bs.getMinimumBasePrice(), bs.getHighestBidPlaced() + bs.getStepRate());

				if (bidAmount >= validBid) {

					try {
						bs.setHighestBidPlaced(bidAmount);
						biddingRepository.save(bs);
					} catch (ObjectOptimisticLockingFailureException ex) {

						log.info("Race condition occured while placing bid");
						return BiddingStatus.BID_CONFLICT;
					}
					return BiddingStatus.BID_ACCEPTED;
				}

				else
					return  BiddingStatus.BID_REJECTED; //ResponseEntity.status(406).body("Bid is Rejected");
			} else
				return BiddingStatus.AUCTION_DOESNT_EXIST; // ResponseEntity.status(404).body("Auction not found");
		} catch (Exception e) {
			log.info("Exception occured while placing bid : "+ e.toString());
			return BiddingStatus.UNKNOWN;
			
		}

	}
}
