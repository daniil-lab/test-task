package com.test.task.util;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private HttpStatus httpStatus;

    public ServiceException() {};

    public ServiceException(String error, HttpStatus httpStatus) {
        super(error);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
