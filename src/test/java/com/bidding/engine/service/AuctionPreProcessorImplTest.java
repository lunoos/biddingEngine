package com.bidding.engine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.entity.AuctionSlotLive;
import com.bidding.engine.entity.Product;
import com.bidding.engine.repository.AuctionSlotLiveRepository;
import com.bidding.engine.repository.ProductRepository;
import com.bidding.engine.utils.AuctionExecutionMap;

@SpringBootTest
class AuctionPreProcessorImplTest {

    @InjectMocks
    private AuctionPreProcessorImpl auctionPreProcessor;

    @Mock
    private FactoryService factoryService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AuctionSlotLiveRepository auLiveRepository;

    @Mock
    private AuctionExecutionMap auctionExecutionMap;

    private AuctionSlot auctionSlot;
    private Product product;
    private BidExecutionDetail bidExecutionDetail;

    @BeforeEach
    void setUp() {
        auctionSlot = new AuctionSlot();
        auctionSlot.setSlotId(1L);
        auctionSlot.setProductId(1L);

        product = new Product();
        product.setProductId(1L);
        product.setBasePrice(new BigDecimal("100"));

        bidExecutionDetail = new BidExecutionDetail();
        bidExecutionDetail.setBidExecutionId("TEST-ID");
    }

    @Test
    void testPreProcess() {
        when(productRepository.getById(auctionSlot.getProductId())).thenReturn(product);
        when(factoryService.generateBidExeDet(auctionSlot, product.getBasePrice())).thenReturn(bidExecutionDetail);
        when(factoryService.geneAuctionSlotLive(bidExecutionDetail)).thenReturn(new AuctionSlotLive());

        BidExecutionDetail result = auctionPreProcessor.preProcess(auctionSlot);

        assertNotNull(result);
        assertEquals(bidExecutionDetail, result);
        verify(auLiveRepository).save(any(AuctionSlotLive.class));
        verify(auctionExecutionMap).add(bidExecutionDetail.getBidExecutionId(), bidExecutionDetail);
    }
}
