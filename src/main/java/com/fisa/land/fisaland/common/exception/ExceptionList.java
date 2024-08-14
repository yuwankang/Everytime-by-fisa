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
    RESOURCE_NOT_FOUND(1005, "Resource not found"),
    
    LENDING_RECORD_NOT_FOUND(2001, "Lending record not found"),
    PRODUCT_NOT_FOUND(2002, "Product not found"),
    BORROWER_SAME_AS_OWNER(2003, "Borrower cannot be the same as owner"),
    PRODUCT_ALREADY_RENTED(2004, "Product is already rented or overdue");

    private final int code;
    private final String message;
}
