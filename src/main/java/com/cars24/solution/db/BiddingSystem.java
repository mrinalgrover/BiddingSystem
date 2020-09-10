package com.cars24.solution.db;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BiddingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int minimumBasePrice;
    private int highestBidPlaced;
    private int stepRate;
    private boolean isRunning;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMinimumBasePrice() {
		return minimumBasePrice;
	}
	public void setMinimumBasePrice(int minimumBasePrice) {
		this.minimumBasePrice = minimumBasePrice;
	}
	public int getHighestBidPlaced() {
		return highestBidPlaced;
	}
	public void setHighestBidPlaced(int highestBidPlaced) {
		this.highestBidPlaced = highestBidPlaced;
	}
	public int getStepRate() {
		return stepRate;
	}
	public void setStepRate(int stepRate) {
		this.stepRate = stepRate;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
    


   
}