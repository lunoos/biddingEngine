package com.bidding.engine.entity;

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
@Table(name = "AUCTION_EXECUTION_STATUS")
public class AuctionExecutionStatus {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_stat_seq")
    @SequenceGenerator(name = "execution_stat_seq", sequenceName = "execution_stat_seq", allocationSize = 1)
    @Column(name = "auction_exe_status_id")
    private Long auctionExeStatusId;

    @Column(name = "auction_exe_id", nullable = false)
    private String auctionExeId;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Column(name = "process_status", nullable = false)
    private String processStatus;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "slot_name", nullable = false)
    private String slotName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @PrePersist
    protected void onCreate() {
    	startTime = LocalDateTime.now();
    }

	public Long getAuctionExeStatusId() {
		return auctionExeStatusId;
	}

	public void setAuctionExeStatusId(Long auctionExeStatusId) {
		this.auctionExeStatusId = auctionExeStatusId;
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

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
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

	@Override
	public String toString() {
		return "AuctionExecutionStatus [auctionExeStatusId=" + auctionExeStatusId + ", auctionExeId=" + auctionExeId
				+ ", slotId=" + slotId + ", processName=" + processName + ", processStatus=" + processStatus
				+ ", productId=" + productId + ", slotName=" + slotName + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

	public AuctionExecutionStatus( String auctionExeId, Long slotId, String processName,
			String processStatus, Long productId, String slotName, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.auctionExeId = auctionExeId;
		this.slotId = slotId;
		this.processName = processName;
		this.processStatus = processStatus;
		this.productId = productId;
		this.slotName = slotName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public AuctionExecutionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
