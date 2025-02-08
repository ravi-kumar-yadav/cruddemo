package com.ravi.cruddemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler()
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(EmployeeNotFoundException exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        log.error("----------------------");
        log.error("EmployeeNotFound Error");
        log.error("----------------------");
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(Exception exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        log.error("---------------------");
        log.error("Internal Server Error");
        log.error("---------------------");
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
