package com.bidding.engine.service;

import com.bidding.engine.entity.AuctionSlot;

public interface AuctionExecutor {
	void executeAuction(AuctionSlot slot);
}
