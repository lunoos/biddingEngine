package com.bidding.engine.service;

import java.util.List;
import java.util.Optional;

import com.bidding.engine.entity.AuctionSlotLive;

public interface AuctionSlotLiveService {

    public AuctionSlotLive createAuctionSlotLive(AuctionSlotLive auctionSlotLive);

	
    public Optional<AuctionSlotLive> getAuctionSlotLiveById(Long liveSlotId);

    public List<AuctionSlotLive> getAuctionSlotLiveByProductId(Long productId);

    public List<AuctionSlotLive> getAuctionSlotLiveBySlotId(Long slotId);

    public AuctionSlotLive updateAuctionSlotLive(AuctionSlotLive auctionSlotLive);

    public void deleteAuctionSlotLive(Long liveSlotId);
}
