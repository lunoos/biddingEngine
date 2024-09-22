package com.bidding.engine.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidExecutionDetail {
	
	

	private String bidExecutionId;
	private LocalDateTime lastUpdated;
	private LocalDateTime createdAt;
	private BigDecimal bidBaseAmount;
	private BigDecimal highestBidAmount;
	private Long highestBidderUserId;
	private Long slotId;
	private Long productId;
	private Character isLive;
	private LocalDateTime slotStartTime;
	private LocalDateTime slotEndTime;
	
	public String getBidExecutionId() {
		return bidExecutionId;
	}
	public void setBidExecutionId(String bidExecutionId) {
		this.bidExecutionId = bidExecutionId;
	}
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public BigDecimal getBidBaseAmount() {
		return bidBaseAmount;
	}
	public void setBidBaseAmount(BigDecimal bidBaseAmount) {
		this.bidBaseAmount = bidBaseAmount;
	}
	public BigDecimal getHighestBidAmount() {
		return highestBidAmount;
	}
	public void setHighestBidAmount(BigDecimal highestBidAmount) {
		this.highestBidAmount = highestBidAmount;
	}
	public Long getHighestBidderUserId() {
		return highestBidderUserId;
	}
	public void setHighestBidderUserId(Long highestBidderUserId) {
		this.highestBidderUserId = highestBidderUserId;
	}
	public Long getSlotId() {
		return slotId;
	}
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Character getIsLive() {
		return isLive;
	}
	public void setIsLive(Character isLive) {
		this.isLive = isLive;
	}
	public LocalDateTime getSlotStartTime() {
		return slotStartTime;
	}
	public void setSlotStartTime(LocalDateTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	public LocalDateTime getSlotEndTime() {
		return slotEndTime;
	}
	public void setSlotEndTime(LocalDateTime slotEndTime) {
		this.slotEndTime = slotEndTime;
	}
	public BidExecutionDetail(String bidExecutionId, LocalDateTime lastUpdated, LocalDateTime createdAt,
			BigDecimal bidBaseAmount, BigDecimal highestBidAmount, Long highestBidderUserId, Long slotId,
			Long productId, Character isLive, LocalDateTime slotStartTime, LocalDateTime slotEndTime) {
		super();
		this.bidExecutionId = bidExecutionId;
		this.lastUpdated = lastUpdated;
		this.createdAt = createdAt;
		this.bidBaseAmount = bidBaseAmount;
		this.highestBidAmount = highestBidAmount;
		this.highestBidderUserId = highestBidderUserId;
		this.slotId = slotId;
		this.productId = productId;
		this.isLive = isLive;
		this.slotStartTime = slotStartTime;
		this.slotEndTime = slotEndTime;
	}
	public BidExecutionDetail() {
		super();
	}

}
