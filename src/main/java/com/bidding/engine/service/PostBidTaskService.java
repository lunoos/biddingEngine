package com.bidding.engine.service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;

public interface PostBidTaskService {
	String executePostBidTask(BidExecutionDetail bidExecutionDetail,BidRequest bidRequest);
}
