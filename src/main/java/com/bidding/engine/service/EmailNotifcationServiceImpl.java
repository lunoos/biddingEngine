package com.bidding.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.User;
import com.bidding.engine.enums.ProcessStatus;
import com.bidding.engine.repository.UserRepository;

@Service
public class EmailNotifcationServiceImpl implements NotificationService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private UserRepository userRepository;
	
	@Value("${app.mail.subject.template}")
	private String subject;
	
	@Value("${app.mail.body.template}")
	private String templateBody;

	@Override
	public String sendNotification(BidExecutionDetail bidExecutionDetail) {
		if (bidExecutionDetail.getHighestBidAmount().compareTo(bidExecutionDetail.getBidBaseAmount()) > 0) {
			User winner = userRepository.findById(bidExecutionDetail.getHighestBidderUserId()).get();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(winner.getEmail());
			message.setSubject(subject);
			message.setText(templateBody
					+ bidExecutionDetail.getHighestBidAmount() + ".");
			emailSender.send(message);
		}
		return ProcessStatus.COMPLETED.name();
	}

}
