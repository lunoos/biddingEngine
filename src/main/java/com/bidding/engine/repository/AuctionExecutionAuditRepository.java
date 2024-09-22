package com.bidding.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bidding.engine.entity.AuctionExecutionAudit;

import java.util.List;

public interface AuctionExecutionAuditRepository extends JpaRepository<AuctionExecutionAudit, Long> {
    List<AuctionExecutionAudit> findByAuctionExeId(String auctionExeId);
    List<AuctionExecutionAudit> findBySlotId(Long slotId);
}