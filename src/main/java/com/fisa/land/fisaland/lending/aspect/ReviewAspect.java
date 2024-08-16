package com.fisa.land.fisaland.lending.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class ReviewAspect {

    private static final Logger LOGGER = Logger.getLogger(ReviewAspect.class.getName());

    @Autowired
    private HttpSession httpSession;

    @AfterReturning(pointcut = "execution(* com.fisa.land.fisaland.lending.service.LendingReviewsService.createReview(..))", returning = "result")
    public void logReview(JoinPoint joinPoint, Object result) {
        LendingReviewsDto reviewDto = (LendingReviewsDto) joinPoint.getArgs()[0];
        Long productId = reviewDto.getProductId();
        Long reviewerId = (Long) httpSession.getAttribute("userId");
        LocalDateTime reviewDate = LocalDateTime.now();

        LOGGER.info("Review Record: Reviewer ID: " + reviewerId + ", Product ID: " + productId + ", Review Date: " + reviewDate + ", Score: " + reviewDto.getRate() + ", Content: " + reviewDto.getContent());
    }
}
