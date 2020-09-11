package com.cars24.solution;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Pageable;

import com.cars24.solution.config.BiddingConfiguration;
import com.cars24.solution.controller.BiddingSystemController;
import com.cars24.solution.model.BiddingSystem;
import com.cars24.solution.model.UserStore;
import com.cars24.solution.repository.BiddingRepository;
import com.cars24.solution.repository.UserRepository;
import com.cars24.solution.service.AuthenticatorService;
import com.cars24.solution.service.BidValidator;
import com.cars24.solution.utility.BiddingStatus;

@WebMvcTest(BiddingSystemController.class)
class BiddingSystemControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BidValidator service;
	
	@MockBean
	private AuthenticatorService auth;
	
	@MockBean
	private BiddingConfiguration conf;
	
	@MockBean
	private BiddingRepository repo;
	
	@MockBean
	private UserRepository user;
	 
	@Autowired
	BiddingRepository biddingRepository;

	@Test
	void auctionTest() throws Exception {

		
		biddingRepository.save(new BiddingSystem(99,1 ));
		when(biddingRepository.findById(1)).thenReturn(new BiddingSystem(99,1 ));
		
		assertThat(service.isValidBid(1, 1000)).isNotEqualTo(BiddingStatus.UNKNOWN);
		
		   
	}
	
	
//	@Test
//	void auctionendTest() throws Exception {
//		when(service.isValidBid(1, 500)).thenReturn(BiddingStatus.BID_ACCEPTED);
//		this.mockMvc.perform(get("/auction")).andDo(print()).andExpect(status().isOk());
	
//	}
	@Test
	void invalidUserTest() throws Exception {
		user.save(new UserStore(303,"abc"));
		
		assertThat(auth.isUserLoggedIn(303, "xyz")).isFalse();
	}
	
	
}
