package com.victorhugo.familyservicemanager.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    public int status;
    public String message;
    public List<FieldValidationError> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;

    }

    public ErrorResponse(int status, String message, List<FieldValidationError> errors) {
        this(status,message);
        this.errors = errors;

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

    public List<FieldValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldValidationError> errors) {
        this.errors = errors;
    }
}
