package com.bidding.engine.utils;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

import com.bidding.engine.entity.AuctionSlot;

@Component
public class AuctionExecutionQueue {
	private ConcurrentLinkedQueue<AuctionSlot> queue = null;

    public AuctionExecutionQueue() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void add(AuctionSlot element) {
        queue.add(element);
    }

    public AuctionSlot poll() {
        return queue.poll();
    }

    public AuctionSlot peek() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
