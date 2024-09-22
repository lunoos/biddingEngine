package com.bidding.engine.service;

import com.bidding.engine.dto.BidExecutionDetail;

public interface AuctionPostProcessor {
	
	public void postProcess(BidExecutionDetail bidExecutionDetail);
}
