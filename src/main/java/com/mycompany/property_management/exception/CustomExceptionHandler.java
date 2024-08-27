package com.mycompany.property_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;

// class to look for handlers when a specific exception is thrown
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        return new ResponseEntity<>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
