package com.bidding.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bidding.engine.entity.Bidder;

public interface BidderRepository extends JpaRepository<Bidder, Long> {
    List<Bidder> findByUserId(Long userId);
    List<Bidder> findBySlotId(Long slotId);
    void deleteBySlotId(Long slotId);
}
