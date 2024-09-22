package com.bidding.engine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.repository.AuctionExecutionAuditRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionExecutionAuditServiceImpl implements AuctionExecutionAuditService{
	
	@Autowired
	private AuctionExecutionAuditRepository auctionExecutionAuditRepository;


	@Transactional
    public AuctionExecutionAudit createExecutionAudit(AuctionExecutionAudit executionAudit) {
        return auctionExecutionAuditRepository.save(executionAudit);
    }

    public Optional<AuctionExecutionAudit> getExecutionAuditById(Long auctionExeAuditId) {
        return auctionExecutionAuditRepository.findById(auctionExeAuditId);
    }

    public List<AuctionExecutionAudit> getExecutionAuditByAuctionExeId(String auctionExeId) {
        return auctionExecutionAuditRepository.findByAuctionExeId(auctionExeId);
    }

}
