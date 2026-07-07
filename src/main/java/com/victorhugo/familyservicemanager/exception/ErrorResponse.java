package com.victorhugo.familyservicemanager.exception;

public class ErrorResponse {
    public int status;
    public String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public ErrorResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
