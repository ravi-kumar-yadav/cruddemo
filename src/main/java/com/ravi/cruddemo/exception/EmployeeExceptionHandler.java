package com.ravi.cruddemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> employeeNotFound(EmployeeNotFoundException employeeNotFoundException){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setMessage(employeeNotFoundException.getMessage());
        employeeErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> genericException(Exception exception){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
