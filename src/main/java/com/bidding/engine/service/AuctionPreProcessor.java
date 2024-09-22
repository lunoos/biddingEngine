package com.bidding.engine.service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.AuctionSlot;

public interface AuctionPreProcessor {
	
	public BidExecutionDetail preProcess(AuctionSlot auctionSlot);

}
