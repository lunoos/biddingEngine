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
import com.bidding.engine.entity.AuctionExecutionAudit;
import com.bidding.engine.enums.ProcessStatus;

@SpringBootTest
class PostBidTaskServiceImplTest {

    @InjectMocks
    private PostBidTaskServiceImpl postBidTaskService;

    @Mock
    private AuctionSlotLiveService auctionSlotLiveService;

    @Mock
    private AuctionExecutionAuditService auctionExecutionAuditService;

    @Mock
    private FactoryService factoryService;

    private BidExecutionDetail bidExecutionDetail;
    private BidRequest bidRequest;

    @BeforeEach
    void setUp() {
        bidExecutionDetail = new BidExecutionDetail();
        bidExecutionDetail.setBidExecutionId("TEST-ID");
        bidExecutionDetail.setHighestBidAmount(new BigDecimal("200"));

        bidRequest = new BidRequest();
        bidRequest.setAuctionExecutionId("TEST-ID");
        bidRequest.setBidAmount(new BigDecimal("200"));
        bidRequest.setUserId(1L);
    }

    @Test
    void testExecutePostBidTask() {
        when(factoryService.genAuctionExecutionAudit(bidExecutionDetail, bidRequest)).thenReturn(new AuctionExecutionAudit());

        String result = postBidTaskService.executePostBidTask(bidExecutionDetail, bidRequest);

        assertEquals(ProcessStatus.IN_PROGRESS.name(), result);
        verify(auctionSlotLiveService).updateHigestBid(bidExecutionDetail);
        verify(auctionExecutionAuditService).createExecutionAudit(any(AuctionExecutionAudit.class));
    }
}