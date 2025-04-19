package com.ravi.cruddemo.exception;

public class EmployeeErrorResponse {
    private long errorCode;
    private String message;
    private long timestamp;

    public EmployeeErrorResponse(){
    }

    public EmployeeErrorResponse(long errorCode, String message, long timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
