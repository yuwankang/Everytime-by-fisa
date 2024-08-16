package com.fisa.land.fisaland.lending.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.land.fisaland.lending.entity.LendingRecords;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class OverdueAspect {

    private static final Logger LOGGER = Logger.getLogger(OverdueAspect.class.getName());

    @Autowired
    private HttpSession httpSession;

    @AfterReturning(pointcut = "execution(* com.fisa.land.fisaland.lending.service.LendingRecordService.updateLendingRecordStatus(..))", returning = "result")
    public void logOverdueRecords(JoinPoint joinPoint, Object result) {
        if (result instanceof LendingRecordInfo) {
            LendingRecordInfo lendingRecordInfo = (LendingRecordInfo) result;
            if (lendingRecordInfo.getStatus() == LendingRecordInfo.LendingStatus.OVERDUE) {
                LendingRecords lendingRecord = lendingRecordInfo.getLendingRecords();
                Long productId = lendingRecord.getProductId();
                Long borrowerId = lendingRecord.getBorrowerId();
                LocalDateTime overdueDate = LocalDateTime.now();

                LOGGER.warning("Overdue Record: Borrower ID: " + borrowerId + ", Product ID: " + productId + ", Overdue Date: " + overdueDate + ", Overdue Fee: " + lendingRecordInfo.getOverdueFee());
            }
        } else {
            LOGGER.warning("Unexpected result type: " + result.getClass().getName());
        }
    }
}
