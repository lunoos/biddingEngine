package com.bidding.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bidding.engine.entity.AuctionSlot;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionSlotRepository extends JpaRepository<AuctionSlot, Long> {
    
	 @Query(value = "select * from auction_slot a inner join products b on a.product_id = b.product_id where b.category_code = :categoryCode", nativeQuery = true)
	List<AuctionSlot> findByCategoryCode(String categoryCode);
	 
	 @Query("SELECT a FROM AuctionSlot a WHERE a.startTime BETWEEN :now AND :fetchUntil ORDER BY a.startTime ASC")
	List<AuctionSlot> findUpcomingSlots(@Param("now") LocalDateTime now, @Param("fetchUntil") LocalDateTime fetchUntil);
}