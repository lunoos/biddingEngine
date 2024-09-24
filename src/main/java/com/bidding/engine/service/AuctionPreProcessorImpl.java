package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.engine.aspect.PreStatusUpdate;
import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.entity.AuctionSlotLive;
import com.bidding.engine.entity.Product;
import com.bidding.engine.repository.AuctionSlotLiveRepository;
import com.bidding.engine.repository.AuctionSlotRepository;
import com.bidding.engine.repository.ProductRepository;
import com.bidding.engine.utils.AuctionExecutionMap;
import com.bidding.engine.utils.IAuctionExecutionMap;

import jakarta.transaction.Transactional;

@Service
public class AuctionPreProcessorImpl implements AuctionPreProcessor{
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AuctionSlotLiveRepository auLiveRepository;
	
	@Autowired
	private IAuctionExecutionMap auctionExecutionMap;

	@Transactional
	@PreStatusUpdate
	@Override
	public BidExecutionDetail preProcess(AuctionSlot auctionSlot) {
		Product product = productRepository.getById(auctionSlot.getProductId());
		BidExecutionDetail bidExecutionDetail = factoryService.generateBidExeDet(auctionSlot,product.getBasePrice());
		// add the slot to live table
		AuctionSlotLive auctionSlotLive = factoryService.geneAuctionSlotLive(bidExecutionDetail);
		auLiveRepository.save(auctionSlotLive);
		// update status table using aspects
		auctionExecutionMap.add(bidExecutionDetail.getBidExecutionId(), bidExecutionDetail);
		return bidExecutionDetail;
	}

}
