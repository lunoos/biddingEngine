package com.bidding.engine.service;

import com.bidding.engine.dto.BidExecutionDetail;

public interface AuctionProcessor {
	public void process(BidExecutionDetail bidExecutionDetail );
}
