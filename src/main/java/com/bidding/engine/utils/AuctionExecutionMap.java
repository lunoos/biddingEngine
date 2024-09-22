package com.bidding.engine.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.bidding.engine.dto.BidExecutionDetail;

@Component
public class AuctionExecutionMap {
	private final ConcurrentMap<String, BidExecutionDetail> map;

    public AuctionExecutionMap() {
        this.map = new ConcurrentHashMap<>();
    }

    public void add(String key, BidExecutionDetail value) {
        map.put(key, value);
    }

    public void delete(String key) {
        map.remove(key);
    }

    public BidExecutionDetail lookup(String key) {
        return map.get(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }
	
}
