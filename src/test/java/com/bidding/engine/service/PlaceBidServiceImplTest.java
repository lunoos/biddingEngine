package com.bidding.engine.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;
import com.bidding.engine.enums.BidResult;
import com.bidding.engine.utils.IAuctionExecutionMap;

@SpringBootTest
class PlaceBidServiceImplTest {

    @InjectMocks
    private PlaceBidServiceImpl placeBidService;

    @Mock
    private IAuctionExecutionMap auctionExecutionMap;

    @Mock
    private FactoryService factoryService;

    @Mock
    private PostBidTaskService postBidTaskService;

    private BidRequest bidRequest;
    private BidExecutionDetail bidExecutionDetail;

    @BeforeEach
    void setUp() {
        bidRequest = new BidRequest();
        bidRequest.setAuctionExecutionId("TEST-ID");
        bidRequest.setBidAmount(new BigDecimal("200"));
        bidRequest.setUserId(1L);

        bidExecutionDetail = new BidExecutionDetail();
        bidExecutionDetail.setBidExecutionId("TEST-ID");
        bidExecutionDetail.setBidBaseAmount(new BigDecimal("100"));
        bidExecutionDetail.setHighestBidAmount(new BigDecimal("150"));
        bidExecutionDetail.setIsLive('Y');

        when(factoryService.geneBidResponse()).thenReturn(new BidResponse());
    }

    @Test
    void testPlaceBid_Success() {
        when(auctionExecutionMap.contains(bidRequest.getAuctionExecutionId())).thenReturn(true);
        when(auctionExecutionMap.lookup(bidRequest.getAuctionExecutionId())).thenReturn(bidExecutionDetail);

        BidResponse response = placeBidService.placeBid(bidRequest);

        assertEquals(BidResult.SUCCESS.name(), response.getResponse());
        assertEquals(bidRequest.getBidAmount(), bidExecutionDetail.getHighestBidAmount());
        assertEquals(bidRequest.getUserId(), bidExecutionDetail.getHighestBidderUserId());
    }

    @Test
    void testPlaceBid_AuctionNotLive() {
        bidExecutionDetail.setIsLive('N');
        when(auctionExecutionMap.contains(bidRequest.getAuctionExecutionId())).thenReturn(true);
        when(auctionExecutionMap.lookup(bidRequest.getAuctionExecutionId())).thenReturn(bidExecutionDetail);

        BidResponse response = placeBidService.placeBid(bidRequest);

        assertEquals(BidResult.AUCTION_NOT_LIVE.name(), response.getResponse());
    }

    @Test
    void testPlaceBid_BidTooLow() {
        bidRequest.setBidAmount(new BigDecimal("50"));
        when(auctionExecutionMap.contains(bidRequest.getAuctionExecutionId())).thenReturn(true);
        when(auctionExecutionMap.lookup(bidRequest.getAuctionExecutionId())).thenReturn(bidExecutionDetail);

        BidResponse response = placeBidService.placeBid(bidRequest);

        assertEquals(BidResult.BID_TOO_LOW.name(), response.getResponse());
    }
}
