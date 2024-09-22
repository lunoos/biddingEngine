package com.bidding.engine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.utils.AuctionExecutionQueue;

@Component
public class AuctionSlotExecuter {
	
	@Autowired
    private AuctionExecutionQueue executionQueue;

	@Autowired
    private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private AuctionExecutor auctionExecutor;

	@Scheduled(fixedDelayString = "${auction.slot.execute.interval}")
    public void processQueueItems() {
        while (!executionQueue.isEmpty() && taskExecutor.getActiveCount() < taskExecutor.getMaxPoolSize()) {
        	AuctionSlot item = executionQueue.poll();
            if (item != null) {
                taskExecutor.execute(() -> auctionExecutor.executeAuction(item));
            }
        }
        System.out.println("in processQueueItems");
    }
}
