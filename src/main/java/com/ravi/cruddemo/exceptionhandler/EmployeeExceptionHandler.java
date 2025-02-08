package com.ravi.cruddemo.exceptionhandler;

import org.apache.coyote.http2.HPackHuffman;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.StubNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(StubNotFoundException exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
