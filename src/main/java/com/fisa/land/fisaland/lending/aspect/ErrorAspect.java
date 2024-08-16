package com.fisa.land.fisaland.lending.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class ErrorAspect {

    private static final Logger LOGGER = Logger.getLogger(ErrorAspect.class.getName());

    @AfterThrowing(pointcut = "within(com.fisa.land.fisaland..*)", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().getName();
        LOGGER.severe("Exception in method: " + method + " with message: " + ex.getMessage());
    }
}
