package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.enums.ProcessStatus;
import com.bidding.engine.repository.AuctionExecutionAuditRepository;
import com.bidding.engine.repository.AuctionSlotLiveRepository;

public class PostBidTaskServiceImpl implements PostBidTaskService{
	
	@Autowired
	private AuctionExecutionAuditRepository auctionExecutionAuditRepository;
	
	@Autowired
	private AuctionSlotLiveRepository auctionSlotLiveRepository;
	
	@Autowired
	private FactoryService factoryService;
	
	@Override
	public String executePostBidTask(final BidExecutionDetail bidExecutionDetail,final BidRequest bidRequest) {
		auctionSlotLiveRepository.updateHighBid(bidExecutionDetail.getHighestBidAmount(),bidExecutionDetail.getBidExecutionId());
		AuctionExecutionAudit auctionExecutionAudit = factoryService.genAuctionExecutionAudit(bidExecutionDetail,bidRequest);
		auctionExecutionAuditRepository.save(auctionExecutionAudit);
		//Can add logic to send latest updates to the user.
		return ProcessStatus.IN_PROGRESS.name();
	}


}
