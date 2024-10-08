package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.aspect.ProcessStatusUpdate;
import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.repository.AuctionSlotRepository;
import com.bidding.engine.repository.BidderRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionPostProcessorImpl implements AuctionPostProcessor{

	@Autowired
	private AuctionSlotRepository auctionSlotRepository;
	
	@Autowired
	private BidderRepository bidderRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@ProcessStatusUpdate
	@Transactional
	@Override
	public void postProcess(BidExecutionDetail bidExecutionDetail) {
		// TODO Auto-generated method stub
		bidderRepository.deleteBySlotId(bidExecutionDetail.getSlotId());
		auctionSlotRepository.deleteById(bidExecutionDetail.getSlotId());
		//Add logic to send mail to the winner.
		notificationService.sendNotification(bidExecutionDetail);
	}

}
