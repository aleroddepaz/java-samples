package org.example.rest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ErrorMessages {

    private Map<String, String> errors = new HashMap<String, String>();

    public ErrorMessages() {
    }

    public ErrorMessages(ConstraintViolationException cve) {
        for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
            addError(violation.getPropertyPath().toString(), violation.getMessage());
        }
    }

    public Object addError(String error, String message) {
        return errors.put(error, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
