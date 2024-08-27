package com.mycompany.property_management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

// class to look for handlers when a specific exception is thrown
@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorsList = manv.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrorsList) {
            logger.info("Inside field validation: {} - {}", error.getField(), error.getDefaultMessage());
            logger.debug("Inside field validation: {} - {}", error.getField(), error.getDefaultMessage());

            errorModel = new ErrorModel();
            errorModel.setCode(error.getCode());
            errorModel.setMessage(error.getDefaultMessage());
            errorModelList.add(errorModel);

        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        for (ErrorModel em : bex.getErrors()) {
            logger.info("BussinessException is thrown: {} - {}", em.getCode(), em.getMessage());
            logger.debug("BussinessException is thrown: {} - {}", em.getCode(), em.getMessage());
        }
        return new ResponseEntity<>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
