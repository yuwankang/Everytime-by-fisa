package com.fisa.land.fisaland.common.exception;

public class BusinessLoginException extends RuntimeException {
	
    private final ExceptionList exceptionList;

    public BusinessLoginException(ExceptionList exceptionList) {
        super(exceptionList.getMessage());
        this.exceptionList = exceptionList;
    }

    public ExceptionList getExceptionList() {
        return exceptionList;
    }
}
