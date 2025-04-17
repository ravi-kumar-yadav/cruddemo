package com.ravi.cruddemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ravi.cruddemo.exception.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(EmployeeNotFoundException employeeNotFoundException){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(employeeNotFoundException.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        log.error("Employee not found exception: " + employeeNotFoundException.getMessage());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(Exception exception){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        employeeErrorResponse.setMessage(exception.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        log.error("Generic exception: " + exception.getMessage());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
