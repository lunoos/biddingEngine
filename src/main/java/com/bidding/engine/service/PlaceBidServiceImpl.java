package com.bidding.engine.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;
import com.bidding.engine.enums.BidResult;
import com.bidding.engine.repository.AuctionSlotLiveRepository;
import com.bidding.engine.utils.AuctionExecutionMap;

import jakarta.transaction.Transactional;

@Service
public class PlaceBidServiceImpl implements PlaceBidService{
	
	@Autowired
	private AuctionExecutionMap auctionExecutionMap;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private AuctionSlotLiveRepository auctionSlotLiveRepository;
	
	@Transactional
	@Override
    public BidResponse placeBid(BidRequest bidRequest) {
		
		BidResponse bidResponse = factoryService.geneBidResponse();
        if (Objects.nonNull(auctionExecutionMap)&&!auctionExecutionMap.contains(bidRequest.getAuctionExecutionId()))
            bidResponse.setResponse(BidResult.AUCTION_NOT_LIVE.name());
        
        BidExecutionDetail bidExecutionDetail = auctionExecutionMap.lookup(bidRequest.getAuctionExecutionId());
        if (bidRequest.getBidAmount().compareTo(bidExecutionDetail.getBidBaseAmount()) <= 0)
        	bidResponse.setResponse(BidResult.BID_TOO_LOW.name());
        
        if (bidRequest.getBidAmount().compareTo(bidExecutionDetail.getHighestBidAmount()) > 0) {
        	synchronized(bidExecutionDetail) {
        	    if (bidRequest.getBidAmount().compareTo(bidExecutionDetail.getHighestBidAmount()) > 0) {
        	        bidExecutionDetail.setHighestBidAmount(bidRequest.getBidAmount());
        	        bidExecutionDetail.setHighestBidderUserId(bidRequest.getUserId());
        	    }
        	}
        }
        //Add logic to call the queue to give it the latest state, it will handle the update of latest score, auditing
        auctionSlotLiveRepository.updateHighBid(bidExecutionDetail.getHighestBidAmount(),bidExecutionDetail.getBidExecutionId());
        return bidResponse;
    }
}
