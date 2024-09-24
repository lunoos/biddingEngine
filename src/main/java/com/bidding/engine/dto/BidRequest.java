package com.bidding.engine.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BidRequest {

	@NotNull(message = "Auction executionId cannot be null")
	private String auctionExecutionId;
	
	@NotNull(message = "BidAmount cannot be null")
	@Min(value = 1, message = "Bid amount must be greater then 0")
	private BigDecimal bidAmount;
	
	@NotNull(message = "UserId cannot be null")
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
