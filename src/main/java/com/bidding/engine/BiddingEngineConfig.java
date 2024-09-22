package com.bidding.engine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bidding.engine.strategy.SlotFetcingStratergy;

@Configuration
@EnableScheduling
public class BiddingEngineConfig {

	@Autowired
    private List<SlotFetcingStratergy> auctionSlotStratergies;
	

	 @Bean
	    public Map<String, SlotFetcingStratergy> slotFetcingStatMap() {
		 Map<String, SlotFetcingStratergy> implementationMap = auctionSlotStratergies.stream().collect(Collectors.toMap(SlotFetcingStratergy::getName, stat -> stat));
		 return implementationMap;
	 }
}
