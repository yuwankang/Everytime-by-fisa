package com.fisa.land.fisaland.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    USER_NOT_FOUND(1001, "User not found"),
    INVALID_PASSWORD(1002, "Invalid password"),
    EMAIL_ALREADY_EXISTS(1003, "Email already exists"),
    USER_NOT_ACTIVATED(1004, "User account is not activated"),
    RESOURCE_NOT_FOUND(1005, "Resource not found");

    private final int code;
    private final String message;
}
