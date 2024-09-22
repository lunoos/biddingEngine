package com.bidding.engine.strategy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.repository.AuctionSlotRepository;

@Component
public class FixedSlotFetcingStratergy implements SlotFetcingStratergy{

    @Value("${auction.slot.fetch.minutes-before-start}")
    private String minutesBeforeStart;
    
    @Value("${auction.slot.fetch.max-slots}")
    private String maxSlots;
    
    @Autowired
	private AuctionSlotRepository auctionSlotRepository;
    
    private final String stratergyName = "fixedSlotFetcingStratergy";
    
    private final Set<Long> fetchedIds = new HashSet<>();
	
	@Override
	public List<AuctionSlot> getUpcomingSlots() {
		LocalDateTime now = LocalDateTime.now();
        LocalDateTime fetchUntil = now.plusMinutes(Long.parseLong(minutesBeforeStart));
        
        List<AuctionSlot> upcomingSlots = auctionSlotRepository.findUpcomingSlots(now, fetchUntil);
        upcomingSlots = upcomingSlots.stream().filter(x->!fetchedIds.contains(x.getSlotId())).map(x->{fetchedIds.add(x.getSlotId()); return x;}).limit(Long.parseLong(maxSlots)).collect(Collectors.toList());
		//clean up logic of fetchedIds set;
        //Can be based on time which are less then current time
        return upcomingSlots;
	}

	@Override
	public String getName() {
		return stratergyName;
	}

}
