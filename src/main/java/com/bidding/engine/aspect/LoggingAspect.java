package com.bidding.engine.aspect;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bidding.engine.dto.BidExecutionDetail;
import com.bidding.engine.entity.AuctionExecutionStatus;
import com.bidding.engine.entity.AuctionSlot;
import com.bidding.engine.enums.ProcessStatus;
import com.bidding.engine.repository.AuctionExecutionStatusRepository;
import com.bidding.engine.service.AuctionExecutionStatusService;

import jakarta.transaction.Transactional;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Autowired
    private AuctionExecutionStatusService auctionExecutionStatusService;

    @Around("execution(* com.insta.backend.service.*.*(..))") // This pointcut expression targets all methods in your package
    public Object logServiceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering method: " + methodName);
        
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        
        logger.info("Exiting method: " + methodName);
        logger.info("Method execution time: " + (endTime - startTime) + " ms");
        
        return result;
    }
    
    @Around("@annotation(PreStatusUpdate)") // This pointcut expression targets all methods in your package
    public Object logExecutionPreStatus(ProceedingJoinPoint joinPoint) throws Throwable {
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Entering method [{}] in class [{}] with arguments {}", 
                    methodName, className, Arrays.toString(args));
        AuctionSlot bidExecutionDetail = (AuctionSlot) args[0];
        long startTime = System.currentTimeMillis();
        AuctionExecutionStatus auctionExecutionStatus = new AuctionExecutionStatus("tmpExecutonId", bidExecutionDetail.getSlotId(),
        		methodName.toUpperCase(), ProcessStatus.IN_PROGRESS.name(), bidExecutionDetail.getProductId(), methodName, LocalDateTime.now(), LocalDateTime.now());
        auctionExecutionStatusService.createExecutionStatus(auctionExecutionStatus);
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        auctionExecutionStatus.setEndTime(LocalDateTime.now());
        BidExecutionDetail bidExecutionDet = (BidExecutionDetail)result;
        auctionExecutionStatus.setAuctionExeId(bidExecutionDet.getBidExecutionId());
        auctionExecutionStatus.setProcessStatus(ProcessStatus.COMPLETED.name());
        auctionExecutionStatusService.updateExecutionStatus(auctionExecutionStatus);
        logger.info("Exiting method [{}] in class [{}] with result: {}",
                    methodName, className, result);
        logger.info("Method [{}] execution time: {} ms", methodName, (endTime - startTime));

        return result;
    }
    
//    @Transactional
//    @Around("@annotation(ProcessStatusUpdate)") // This pointcut expression targets all methods in your package
//    public Object logExecutionProcessStatus(ProceedingJoinPoint joinPoint) throws Throwable {
//    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//    	String methodName = methodSignature.getName();
//        Object[] args = joinPoint.getArgs();
//
//       BidExecutionDetail bidExecutionDetail = (BidExecutionDetail) args[0];
//        AuctionExecutionStatus auctionExecutionStatus = new AuctionExecutionStatus(bidExecutionDetail.getBidExecutionId(), bidExecutionDetail.getSlotId(),
//        		methodName.toUpperCase(), ProcessStatus.IN_PROGRESS.name(), bidExecutionDetail.getProductId(), methodName, LocalDateTime.now(), null);
//        auctionExecutionStatusRepository.save(auctionExecutionStatus);
//        Object result = joinPoint.proceed();
//        auctionExecutionStatus.setEndTime(LocalDateTime.now());
//        auctionExecutionStatus.setProcessStatus(ProcessStatus.COMPLETED.name());
//        auctionExecutionStatusRepository.save(auctionExecutionStatus);
//        
//        return result;
//    }
    
    @Before("@annotation(ProcessStatusUpdate)") // This pointcut expression targets all methods in your package
    public void logExecutionProcessStatusBefore(JoinPoint joinPoint) throws Throwable {
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    	String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

       BidExecutionDetail bidExecutionDetail = (BidExecutionDetail) args[0];
        AuctionExecutionStatus auctionExecutionStatus = new AuctionExecutionStatus(bidExecutionDetail.getBidExecutionId(), bidExecutionDetail.getSlotId(),
        		methodName.toUpperCase(), ProcessStatus.IN_PROGRESS.name(), bidExecutionDetail.getProductId(), methodName, LocalDateTime.now(), null);
        auctionExecutionStatusService.createExecutionStatus(auctionExecutionStatus);
    }
    
    @After("@annotation(ProcessStatusUpdate)") // This pointcut expression targets all methods in your package
    public void logExecutionProcessStatusAfter(JoinPoint joinPoint) throws Throwable {
       Object[] args = joinPoint.getArgs();
       BidExecutionDetail bidExecutionDetail = (BidExecutionDetail) args[0];
       auctionExecutionStatusService.updateStatusEndTime(ProcessStatus.COMPLETED.name(),LocalDateTime.now(),bidExecutionDetail.getBidExecutionId());
        
    }
}
