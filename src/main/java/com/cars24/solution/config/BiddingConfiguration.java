package com.cars24.solution.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "bid")
public class BiddingConfiguration {

	@Value("${bid.conflictRetry:0}")
	private int conflictRetry;

	public int getConflictRetry() {
		return conflictRetry;
	}

	public void setConflictRetry(int conflictRetry) {
		this.conflictRetry = conflictRetry;
	}
}
