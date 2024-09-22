package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.AuctionSlot;

@Component
public class AuctionExecutorImpl implements AuctionExecutor {

	@Autowired
	private AuctionPreProcessor preProcessor;
	@Autowired
    private AuctionProcessor processor;
	@Autowired
    private AuctionPostProcessor postProcessor;
 
    @Override
    public void executeAuction(AuctionSlot slot) {
    	BidExecutionDetail bidExecutionDetail = preProcessor.preProcess(slot);
        processor.process(bidExecutionDetail);
        postProcessor.postProcess(bidExecutionDetail);
    }
}
