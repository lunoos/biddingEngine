package com.bidding.engine.strategy;

import java.util.List;

import com.bidding.engine.entity.AuctionSlot;

public interface SlotFetcingStratergy {

	List<AuctionSlot> getUpcomingSlots();
	String getName();
}
