package com.mitskevich.syberrytesttask.exception.errors;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Set;


/**
 * Data Transfer Object (DTO) for representing a structured error response.
 */
@Getter
@Builder
public class StructuredError {

    /**
     * The HTTP status associated with the error.
     */
    private final HttpStatus status;

    /**
     * The URI where the error occurred.
     */
    private final String uri;

    /**
     * Set of error messages associated with the error.
     */
    private final Set<ErrorMessage> errors;
}
