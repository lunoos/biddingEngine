package com.bidding.engine.service;

import java.math.BigDecimal;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;
import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.entity.AuctionSlotLive;

public interface FactoryService {
	BidExecutionDetail generateBidExeDet(AuctionSlot AuctionSlot,BigDecimal basePrice);
	AuctionSlotLive geneAuctionSlotLive(BidExecutionDetail bidExecutionDetail);
	BidResponse geneBidResponse();
	AuctionExecutionAudit genAuctionExecutionAudit(BidExecutionDetail bidExecutionDetail,BidRequest bidRequest);
}
