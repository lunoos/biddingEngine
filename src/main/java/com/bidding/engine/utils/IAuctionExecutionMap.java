package com.bidding.engine.utils;

import com.bidding.engine.dto.BidExecutionDetail;

public interface IAuctionExecutionMap {
	void add(String key, BidExecutionDetail value);

    void delete(String key);

    BidExecutionDetail lookup(String key);

    boolean contains(String key);

    int size();
}
