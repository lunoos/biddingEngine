package com.bidding.engine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.entity.AuctionExecutionStatus;
import com.bidding.engine.repository.AuctionExecutionStatusRepository;

@Service
public class AuctionExecutionStatusServiceImpl implements AuctionExecutionStatusService {
	
	@Autowired
	private AuctionExecutionStatusRepository auctionExecutionStatusRepository;

    public AuctionExecutionStatus createExecutionStatus(AuctionExecutionStatus executionStatus) {
        return auctionExecutionStatusRepository.save(executionStatus);
    }

    public List<AuctionExecutionStatus> getExecutionStatusByAuctionExeId(String auctionExeId) {
        return auctionExecutionStatusRepository.findByAuctionExeId(auctionExeId);
    }

    public AuctionExecutionStatus updateExecutionStatus(AuctionExecutionStatus executionStatus) {
        return auctionExecutionStatusRepository.save(executionStatus);
    }

}
