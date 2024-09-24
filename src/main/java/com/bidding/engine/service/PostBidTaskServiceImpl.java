package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.enums.ProcessStatus;
import com.bidding.engine.repository.AuctionExecutionAuditRepository;
import com.bidding.engine.repository.AuctionSlotLiveRepository;

import jakarta.transaction.Transactional;

@Service
public class PostBidTaskServiceImpl implements PostBidTaskService{
	
	@Autowired
	private AuctionSlotLiveService auctionSlotLiveService;
	
	@Autowired
	private AuctionExecutionAuditService auctionExecutionAuditService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Override
	public String executePostBidTask(final BidExecutionDetail bidExecutionDetail,final BidRequest bidRequest) {
		auctionSlotLiveService.updateHigestBid(bidExecutionDetail);
		AuctionExecutionAudit auctionExecutionAudit = factoryService.genAuctionExecutionAudit(bidExecutionDetail,bidRequest);
		auctionExecutionAuditService.createExecutionAudit(auctionExecutionAudit);
		//Can add logic to send latest updates to the user.
		return ProcessStatus.IN_PROGRESS.name();
	}


}
