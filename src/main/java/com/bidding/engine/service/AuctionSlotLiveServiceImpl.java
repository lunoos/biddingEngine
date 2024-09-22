package com.bidding.engine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.entity.AuctionSlotLive;
import com.bidding.engine.repository.AuctionSlotLiveRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionSlotLiveServiceImpl implements  AuctionSlotLiveService{

	@Autowired
	private AuctionSlotLiveRepository auctionSlotLiveRepository;

	@Transactional
    public AuctionSlotLive createAuctionSlotLive(AuctionSlotLive auctionSlotLive) {
        return auctionSlotLiveRepository.save(auctionSlotLive);
    }

	
    public Optional<AuctionSlotLive> getAuctionSlotLiveById(Long liveSlotId) {
        return auctionSlotLiveRepository.findById(liveSlotId);
    }

    public List<AuctionSlotLive> getAuctionSlotLiveByProductId(Long productId) {
        return auctionSlotLiveRepository.findByProductId(productId);
    }

    public List<AuctionSlotLive> getAuctionSlotLiveBySlotId(Long slotId) {
        return auctionSlotLiveRepository.findBySlotId(slotId);
    }

    @Transactional
    public AuctionSlotLive updateAuctionSlotLive(AuctionSlotLive auctionSlotLive) {
        return auctionSlotLiveRepository.save(auctionSlotLive);
    }

    @Transactional
    public void deleteAuctionSlotLive(Long liveSlotId) {
        auctionSlotLiveRepository.deleteById(liveSlotId);
    }
}
