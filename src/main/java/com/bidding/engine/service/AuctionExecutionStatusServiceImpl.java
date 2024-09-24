package com.bidding.engine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.bidding.engine.entity.AuctionExecutionStatus;
import com.bidding.engine.repository.AuctionExecutionStatusRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionExecutionStatusServiceImpl implements AuctionExecutionStatusService {
	
	@Autowired
	private AuctionExecutionStatusRepository auctionExecutionStatusRepository;

	@Transactional
    public AuctionExecutionStatus createExecutionStatus(AuctionExecutionStatus executionStatus) {
        return auctionExecutionStatusRepository.save(executionStatus);
    }

    public List<AuctionExecutionStatus> getExecutionStatusByAuctionExeId(String auctionExeId) {
        return auctionExecutionStatusRepository.findByAuctionExeId(auctionExeId);
    }

    public AuctionExecutionStatus updateExecutionStatus(AuctionExecutionStatus executionStatus) {
        return auctionExecutionStatusRepository.save(executionStatus);
    }
    
    @Transactional
    public void updateStatusEndTime(String processStatus,LocalDateTime endTime, String bidExecutionId) {
    	auctionExecutionStatusRepository.updateStatusEndTime(processStatus,bidExecutionId);
    }

}
