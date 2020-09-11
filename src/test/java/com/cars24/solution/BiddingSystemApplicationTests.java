package com.cars24.solution;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.cars24.solution.controller.BiddingSystemController;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
class BiddingSystemApplicationTests {

	@Autowired
	private BiddingSystemController controller;
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
