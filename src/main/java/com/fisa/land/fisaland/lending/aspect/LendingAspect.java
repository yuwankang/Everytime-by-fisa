package com.fisa.land.fisaland.lending.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import com.fisa.land.fisaland.lending.entity.LendingRecords;

@Aspect
@Component
public class LendingAspect {

    private static final Logger LOGGER = Logger.getLogger(LendingAspect.class.getName());

    @Autowired
    private HttpSession httpSession;

    @AfterReturning(pointcut = "execution(* com.fisa.land.fisaland.lending.service.LendingRecordService.saveLendingRecord(..))", returning = "result")
    public void logLendingRecord(JoinPoint joinPoint, Object result) {
        if (result instanceof LendingRecords) {
            LendingRecords lendingRecord = (LendingRecords) result;
            Long borrowerId = lendingRecord.getBorrowerId();
            Long productId = lendingRecord.getProductId();
            LocalDateTime borrowedDate = lendingRecord.getCreated(); // 대여 기록 생성 시간을 대여 날짜로 사용

            LOGGER.info("Lending Record: Borrower ID: " + borrowerId + ", Product ID: " + productId + ", Borrowed Date: " + borrowedDate);
        }
    }
}
