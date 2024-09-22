package com.bidding.engine.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUCTION_EXECUTION_AUDIT")
public class AuctionExecutionAudit {
    @Id
    @Column(name = "auction_exe_audit_id")
    private Long auctionExeAuditId;

    @Column(name = "auction_exe_id", nullable = false)
    private String auctionExeId;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "bidder_id", nullable = false)
    private Long bidderId;

    @Column(name = "bid_amount", nullable = false, precision = 9, scale = 2)
    private BigDecimal bidAmount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
    public Long getAuctionExeAuditId() {
		return auctionExeAuditId;
	}

    
	public AuctionExecutionAudit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setAuctionExeAuditId(Long auctionExeAuditId) {
		this.auctionExeAuditId = auctionExeAuditId;
	}


	public String getAuctionExeId() {
		return auctionExeId;
	}


	public void setAuctionExeId(String auctionExeId) {
		this.auctionExeId = auctionExeId;
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


	public Long getBidderId() {
		return bidderId;
	}


	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}


	public BigDecimal getBidAmount() {
		return bidAmount;
	}


	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
