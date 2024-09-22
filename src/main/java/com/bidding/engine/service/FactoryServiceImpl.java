package com.bidding.engine.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;
import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.entity.AuctionSlotLive;

@Service
public class FactoryServiceImpl implements FactoryService{

	
	@Override
	public BidExecutionDetail generateBidExeDet(AuctionSlot auctionSlot,BigDecimal basePrice) {
		BidExecutionDetail bidExecutionDetail = new BidExecutionDetail(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), basePrice, basePrice, null, auctionSlot.getSlotId(), auctionSlot.getProductId(), 'N', auctionSlot.getStartTime(), auctionSlot.getEndTime());
		return bidExecutionDetail;
	}

	@Override
	public AuctionSlotLive geneAuctionSlotLive(BidExecutionDetail bidExecutionDetail) {
		return new AuctionSlotLive(bidExecutionDetail.getSlotId(), bidExecutionDetail.getProductId(), null, 
				bidExecutionDetail.getSlotStartTime(), bidExecutionDetail.getSlotEndTime(), 
				null,bidExecutionDetail.getBidBaseAmount(),bidExecutionDetail.getHighestBidAmount(),"N",bidExecutionDetail.getBidExecutionId());
	}

	@Override
	public BidResponse geneBidResponse() {
		return new BidResponse();
	}

	@Override
	public AuctionExecutionAudit genAuctionExecutionAudit(BidExecutionDetail bidExecutionDetail,BidRequest bidRequest) {
		return new AuctionExecutionAudit(bidExecutionDetail.getBidExecutionId(), bidExecutionDetail.getSlotId(), bidExecutionDetail.getProductId(), bidRequest.getUserId(), bidRequest.getBidAmount(), LocalDateTime.now());
	}

}
