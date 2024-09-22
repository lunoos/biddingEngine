package com.bidding.engine.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.aspect.ProcessStatusUpdate;
import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.repository.AuctionSlotLiveRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionProcessorImpl implements AuctionProcessor{


	@Autowired
	private AuctionSlotLiveRepository auLiveRepository;
	
	@ProcessStatusUpdate
	@Transactional
	@Override
	public void process(BidExecutionDetail bidExecutionDetail) {
		System.out.println("waiting for process to start");
		waitUntil(bidExecutionDetail.getSlotStartTime());
		bidExecutionDetail.setIsLive('Y');
		auLiveRepository.updateLiveStatus("Y",bidExecutionDetail.getBidExecutionId());
		System.out.println("waiting for process to end");
		
        waitUntil(bidExecutionDetail.getSlotEndTime());
        bidExecutionDetail.setIsLive('N');
        auLiveRepository.updateLiveStatus("N",bidExecutionDetail.getBidExecutionId());
        System.out.println("waiting for process ended");
	}
	
	private void waitUntil(LocalDateTime targetTime) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(targetTime)) {
            Duration duration = Duration.between(now, targetTime);
            try {
                TimeUnit.SECONDS.sleep(duration.getSeconds());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
