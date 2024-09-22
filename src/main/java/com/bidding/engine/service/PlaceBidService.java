package com.bidding.engine.service;

import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;

public interface PlaceBidService {
	public BidResponse placeBid(BidRequest bidRequest);
}
