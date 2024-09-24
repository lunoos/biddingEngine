package com.bidding.engine.service;

import com.bidding.engine.dto.BidExecutionDetail;

public interface NotificationService {
	String sendNotification(BidExecutionDetail bidExecutionDetail);
}
