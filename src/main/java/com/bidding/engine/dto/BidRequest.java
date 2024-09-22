package com.bidding.engine.dto;

import java.math.BigDecimal;

public class BidRequest {

	private String auctionExecutionId;
	private BigDecimal bidAmount;
	private Long userId;

	public String getAuctionExecutionId() {
		return auctionExecutionId;
	}

	public void setAuctionExecutionId(String auctionExecutionId) {
		this.auctionExecutionId = auctionExecutionId;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
