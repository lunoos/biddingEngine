package com.bidding.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bidding.engine.entity.AuctionExecutionStatus;

import java.util.List;

public interface AuctionExecutionStatusRepository extends JpaRepository<AuctionExecutionStatus, Long> {
    List<AuctionExecutionStatus> findByAuctionExeId(String auctionExeId);
    List<AuctionExecutionStatus> findBySlotId(Long slotId);
}
