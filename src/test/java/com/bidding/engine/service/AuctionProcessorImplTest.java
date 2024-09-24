package com.bidding.engine.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bidding.engine.dto.BidExecutionDetail;

@SpringBootTest
class AuctionProcessorImplTest {

    @InjectMocks
    private AuctionProcessorImpl auctionProcessor;

    @Mock
    private AuctionSlotLiveService auctionSlotLiveService;

    private BidExecutionDetail bidExecutionDetail;

    @BeforeEach
    void setUp() {
        bidExecutionDetail = new BidExecutionDetail();
        bidExecutionDetail.setSlotStartTime(LocalDateTime.now().plusSeconds(1));
        bidExecutionDetail.setSlotEndTime(LocalDateTime.now().plusSeconds(2));
    }

    @Test
    void testProcess() {
        auctionProcessor.process(bidExecutionDetail);

        verify(auctionSlotLiveService).updateLiveStatus(bidExecutionDetail, 'Y');
        verify(auctionSlotLiveService).updateLiveStatus(bidExecutionDetail, 'N');
    }
}