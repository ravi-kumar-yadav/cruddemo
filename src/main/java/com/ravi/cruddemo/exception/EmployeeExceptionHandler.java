package com.ravi.cruddemo.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(EmployeeNotFoundException employeeNotFoundException){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(employeeNotFoundException.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> exceptionHandler(Exception exception){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exception.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }
}
