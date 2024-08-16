package com.fisa.land.fisaland.lending.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class UserActivityAspect {

    private static final Logger LOGGER = Logger.getLogger(UserActivityAspect.class.getName());

    @Autowired
    private HttpSession httpSession;

    @After("execution(* com.fisa.land.fisaland.page.MainPageController.*(..))")
    public void logUserActivity(JoinPoint joinPoint) {
        Long userId = (Long) httpSession.getAttribute("userId");
        String activity = joinPoint.getSignature().getName();
        LocalDateTime activityDate = LocalDateTime.now();

        LOGGER.info("User Activity: User ID: " + userId + ", Activity: " + activity + ", Date: " + activityDate);
    }
}
