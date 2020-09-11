package com.cars24.solution.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

enum Status{
	RUNNING,
	OVER
}

@Entity
public class BiddingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int minimumBasePrice;
    private int highestBidPlaced = 0;
    private int stepRate = 20;
    private Status isRunning = Status.RUNNING;
    
    //For optimistic locking
    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Version
    private Integer version;
    
    protected BiddingSystem() {}

    public BiddingSystem(int minimumBasePrice, int stepRate) {
      this.minimumBasePrice = minimumBasePrice;
      this.stepRate = stepRate;
    }

    @Override
    public String toString() {
      return String.format(
          "BiddingSystem[id=%d, minimumBasePrice='%s', stepRate='%s']",
          id, minimumBasePrice, stepRate);
    }
    
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

	public Status getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Status isRunning) {
		this.isRunning = isRunning;
	}
	
    


   
}