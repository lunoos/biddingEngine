package com.bidding.engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.engine.entity.AuctionSlotLive;
import com.bidding.engine.service.AuctionSlotLiveService;

@RestController
@RequestMapping("/api/auction-slot-live")
public class AuctionSlotLiveController {

    private final AuctionSlotLiveService auctionSlotLiveService;

    @Autowired
    public AuctionSlotLiveController(AuctionSlotLiveService auctionSlotLiveService) {
        this.auctionSlotLiveService = auctionSlotLiveService;
    }

    @PostMapping
    public ResponseEntity<AuctionSlotLive> createAuctionSlotLive(@RequestBody AuctionSlotLive auctionSlotLive) {
        AuctionSlotLive createdSlot = auctionSlotLiveService.createAuctionSlotLive(auctionSlotLive);
        return new ResponseEntity<>(createdSlot, HttpStatus.CREATED);
    }

    @GetMapping("/{liveSlotId}")
    public ResponseEntity<AuctionSlotLive> getAuctionSlotLiveById(@PathVariable Long liveSlotId) {
        return auctionSlotLiveService.getAuctionSlotLiveById(liveSlotId)
                .map(slot -> new ResponseEntity<>(slot, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<AuctionSlotLive>> getAuctionSlotLiveByProductId(@PathVariable Long productId) {
        List<AuctionSlotLive> slots = auctionSlotLiveService.getAuctionSlotLiveByProductId(productId);
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    @GetMapping("/slot/{slotId}")
    public ResponseEntity<List<AuctionSlotLive>> getAuctionSlotLiveBySlotId(@PathVariable Long slotId) {
        List<AuctionSlotLive> slots = auctionSlotLiveService.getAuctionSlotLiveBySlotId(slotId);
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    @PutMapping("/{liveSlotId}")
    public ResponseEntity<AuctionSlotLive> updateAuctionSlotLive(@PathVariable Long liveSlotId, @RequestBody AuctionSlotLive auctionSlotLive) {
        auctionSlotLive.setLiveSlotId(liveSlotId);
        AuctionSlotLive updatedSlot = auctionSlotLiveService.updateAuctionSlotLive(auctionSlotLive);
        return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
    }

    @DeleteMapping("/{liveSlotId}")
    public ResponseEntity<Void> deleteAuctionSlotLive(@PathVariable Long liveSlotId) {
        auctionSlotLiveService.deleteAuctionSlotLive(liveSlotId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
