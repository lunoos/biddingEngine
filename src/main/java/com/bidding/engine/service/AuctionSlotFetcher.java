package com.bidding.engine.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.strategy.SlotFetcingStratergy;
import com.bidding.engine.utils.AuctionExecutionQueue;

@Component
public class AuctionSlotFetcher {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionSlotFetcher.class);
	
	@Autowired
    private AuctionExecutionQueue executionQueue;
	
	@Value("${app.slotFetcingStratergy}")
	private String slotFetcingStratergy;
	
	@Autowired
	private Map<String, SlotFetcingStratergy> slotFetcingStatMap;


    @Scheduled(fixedDelayString = "${auction.slot.fetch.interval}")
    public void fetchUpcomingSlots() {
    	
        List<AuctionSlot> upcomingSlots = slotFetcingStatMap.get(slotFetcingStratergy).getUpcomingSlots();
        
        for (AuctionSlot slot : upcomingSlots) {
                executionQueue.add(slot);
        }
        logger.info("AuctionSlotFetcher is running.");
    }
}
