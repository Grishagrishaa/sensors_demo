package com.mitskevich.sensorstesttask.exception;

import com.mitskevich.sensorstesttask.exception.errors.ErrorMessage;
import com.mitskevich.sensorstesttask.exception.errors.StructuredError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handle(IllegalArgumentException e, HttpServletRequest req){
        return ResponseEntity
                .badRequest()
                .body(buildErrorMessage(e.getMessage(), BAD_REQUEST, req.getRequestURI()));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handle(EntityNotFoundException e, HttpServletRequest req){
        return ResponseEntity
                .status(NO_CONTENT)
                .body(buildErrorMessage("Entity not found", NO_CONTENT, req.getRequestURI()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredError> handle(ConstraintViolationException e, HttpServletRequest req){

        Set<ErrorMessage> errorMessages = e.getConstraintViolations().stream()
                .map(violation -> ErrorMessage.builder()
                        .uri(StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                .reduce((first, second) -> second)
                                .orElse(null)
                                .toString())
                        .message(violation.getMessage())
                        .build())
                .collect(toSet());

        return ResponseEntity.badRequest().body(StructuredError.builder()
                .errors(errorMessages)
                .status(BAD_REQUEST)
                .uri(req.getRequestURI())
                .build());
    }

    private ErrorMessage buildErrorMessage(String message, HttpStatus status, String uri){
        return ErrorMessage.builder()
                .message(message)
                .status(status)
                .uri(uri)
                .build();
    }
}
