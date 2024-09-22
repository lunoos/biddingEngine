package com.bidding.engine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.engine.dto.BidRequest;
import com.bidding.engine.dto.BidResponse;

@RestController
@RequestMapping("/bid")
public class BiddingController {
	
	@PostMapping
	public ResponseEntity<BidResponse> placeBid(@RequestBody BidRequest bidRequest){
		return new ResponseEntity<>(new BidResponse("Bid has been placed"), HttpStatus.CREATED);
	}
}
