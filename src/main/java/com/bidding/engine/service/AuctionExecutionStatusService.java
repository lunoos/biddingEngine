package com.bidding.engine.service;

import java.util.List;

import com.bidding.engine.entity.AuctionExecutionStatus;

public interface AuctionExecutionStatusService {

	public AuctionExecutionStatus createExecutionStatus(AuctionExecutionStatus executionStatus);

    public List<AuctionExecutionStatus> getExecutionStatusByAuctionExeId(String auctionExeId);

    public AuctionExecutionStatus updateExecutionStatus(AuctionExecutionStatus executionStatus);
}
