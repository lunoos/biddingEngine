package com.bidding.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bidding.engine.entity.AuctionExecutionStatus;

import java.util.List;

public interface AuctionExecutionStatusRepository extends JpaRepository<AuctionExecutionStatus, Long> {
    List<AuctionExecutionStatus> findByAuctionExeId(String auctionExeId);
    List<AuctionExecutionStatus> findBySlotId(Long slotId);
    @Modifying
   	@Query("update AuctionExecutionStatus e set e.processStatus = :processStatus where auctionExeId = :bidExecutionId")
   	public void updateStatusEndTime(@Param("processStatus") String processStatus,@Param("bidExecutionId") String bidExecutionId);
}
