package com.mapbomoi.coursemanagement.common.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class RestResponseExeptionHandler extends ResponseEntityExceptionHandler {
    private final String INTERNAL_SERVER_ERROR = "Unexpected error happened on our system";

    @ExceptionHandler({ InvalidRequestException.class })
    public ResponseEntity<Object> handleInvalidRequest(InvalidRequestException e, WebRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
        List<String> errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleGeneralException(Exception e, WebRequest request) {
        return ResponseEntity.internalServerError().body(INTERNAL_SERVER_ERROR);
    }
}
