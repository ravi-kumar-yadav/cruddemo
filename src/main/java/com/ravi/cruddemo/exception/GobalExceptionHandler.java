package com.ravi.cruddemo.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> employeeNotFoundException(EmployeeNotFoundException exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> employeeGenericException(Exception exp){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exp.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
