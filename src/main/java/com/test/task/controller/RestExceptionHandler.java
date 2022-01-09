package com.test.task.controller;

import com.test.task.util.ServiceException;
import com.test.task.util.ServiceResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceResponse<StackTraceElement[]>> handleServiceException(ServiceException e) {
        return new ResponseEntity<>(new ServiceResponse<>(
                e.getHttpStatus().value(), e.getStackTrace(), e.getMessage()), e.getHttpStatus());
    }
}
