package com.ravi.cruddemo.exception;

public class EmployeeErrorResponse {

    private String message;
    private long timestamp;
    private long statusCode;

    public EmployeeErrorResponse(){
    }

    public EmployeeErrorResponse(String message, long timestamp, long statusCode) {
        this.message = message;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

}
