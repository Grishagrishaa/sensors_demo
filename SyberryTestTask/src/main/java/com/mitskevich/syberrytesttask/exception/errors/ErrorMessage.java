package com.mitskevich.syberrytesttask.exception.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Data Transfer Object (DTO) for representing an error message.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    /**
     * The HTTP status associated with the error.
     */
    private final HttpStatus status;

    /**
     * The URI where the error occurred.
     */
    private final String uri;

    /**
     * The error message.
     */
    private final String message;

}