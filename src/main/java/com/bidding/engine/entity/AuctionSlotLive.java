package com.bidding.engine.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUCTION_SLOT_LIVE")
public class AuctionSlotLive {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_slot_live_seq")
    @SequenceGenerator(name = "auction_slot_live_seq", sequenceName = "auction_slot_live_seq", allocationSize = 1)
    @Column(name = "live_slot_id")
    private Long liveSlotId;

    @Column(name = "slot_id")
    private Long slotId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "slot_name")
    private String slotName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "base_price", precision = 9, scale = 2)
    private BigDecimal basePrice;
    
    @Column(name = "current_price", precision = 9, scale = 2)
    private BigDecimal currentPrice;
    
    @Column(name = "is_live", nullable = false)
    private String isLive;
    
    @Column(name = "auction_exe_id", nullable = false)
    private String bidExecutionId;
    
    public Long getLiveSlotId() {
		return liveSlotId;
	}



	public void setLiveSlotId(Long liveSlotId) {
		this.liveSlotId = liveSlotId;
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



	public String getSlotName() {
		return slotName;
	}



	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public BigDecimal getBasePrice() {
		return basePrice;
	}



	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}



	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}



	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getIsLive() {
		return isLive;
	}



	public void setIsLive(String isLive) {
		this.isLive = isLive;
	}



	public AuctionSlotLive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuctionSlotLive(Long slotId, Long productId, String slotName, LocalDateTime startTime, LocalDateTime endTime,
			LocalDateTime createdAt, BigDecimal basePrice, BigDecimal currentPrice, String isLive,String bidExecutionId) {
		super();
		this.slotId = slotId;
		this.productId = productId;
		this.slotName = slotName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
		this.basePrice = basePrice;
		this.currentPrice = currentPrice;
		this.isLive = isLive;
		this.bidExecutionId=bidExecutionId;
	}



	public String getBidExecutionId() {
		return bidExecutionId;
	}



	public void setBidExecutionId(String bidExecutionId) {
		this.bidExecutionId = bidExecutionId;
	}



	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
