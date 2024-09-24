package com.bidding.engine.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.repository.AuctionSlotRepository;
import com.bidding.engine.repository.BidderRepository;

@SpringBootTest
class AuctionPostProcessorImplTest {

    @InjectMocks
    private AuctionPostProcessorImpl auctionPostProcessor;

    @Mock
    private AuctionSlotRepository auctionSlotRepository;

    @Mock
    private BidderRepository bidderRepository;

    @Mock
    private NotificationService notificationService;

    private BidExecutionDetail bidExecutionDetail;

    @BeforeEach
    void setUp() {
        bidExecutionDetail = new BidExecutionDetail();
        bidExecutionDetail.setSlotId(1L);
    }

    @Test
    void testPostProcess() {
        auctionPostProcessor.postProcess(bidExecutionDetail);
        
        verify(bidderRepository).deleteBySlotId(bidExecutionDetail.getSlotId());
        verify(auctionSlotRepository).deleteById(bidExecutionDetail.getSlotId());
        verify(notificationService).sendNotification(bidExecutionDetail);
    }
}