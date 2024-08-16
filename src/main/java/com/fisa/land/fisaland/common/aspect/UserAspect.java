package com.fisa.land.fisaland.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisa.land.fisaland.common.entity.User;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class UserAspect {

    private static final Logger LOGGER = Logger.getLogger(UserAspect.class.getName());

    @Autowired
    private HttpSession httpSession;

    @AfterReturning(pointcut = "execution(* com.fisa.land.fisaland.common.service.UserService.login(..))", returning = "result")
    public void logUserLogin(JoinPoint joinPoint, Object result) {
        Long userId = (Long) httpSession.getAttribute("userId");
        String method = joinPoint.getSignature().getName();
        LocalDateTime loginDate = LocalDateTime.now();

        LOGGER.info("User Login: User ID: " + userId + ", Method: " + method + ", Date: " + loginDate);
    }

    @AfterReturning(pointcut = "execution(* com.fisa.land.fisaland.common.service.UserService.updateUser(..))", returning = "result")
    public void logUserUpdate(JoinPoint joinPoint, Object result) {
        User user = (User) result;
        Long userId = user.getUserId();
        LocalDateTime updateDate = LocalDateTime.now();

        LOGGER.info("User Update: User ID: " + userId + ", Updated At: " + updateDate);
    }
}
