package com.bidding.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bidding.engine.entity.AuctionSlotLive;

import java.util.List;

public interface AuctionSlotLiveRepository extends JpaRepository<AuctionSlotLive, Long> {
    List<AuctionSlotLive> findByProductId(Long productId);
    List<AuctionSlotLive> findBySlotId(Long slotId);
    @Modifying
	@Query("update AuctionSlotLive e set e.isLive = :status where bidExecutionId = :bidExecutionId")
	public void updateLiveStatus(@Param("status") String status,@Param("bidExecutionId") String bidExecutionId);
}
