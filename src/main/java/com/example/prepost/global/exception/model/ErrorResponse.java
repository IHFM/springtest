package com.example.prepost.global.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String code,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp,
        String exception,
        List<FieldValidationError> fieldErrors
) {
    public record FieldValidationError(String field, String message) {}

    public static ErrorResponse of(ErrorCode errorCode, String message, Exception e) {
        return new ErrorResponse(
                errorCode.getStatus().value(),
                errorCode.name(),
                message,
                LocalDateTime.now(),
                e.getClass().getSimpleName(),
                List.of()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String message, Exception e, List<FieldValidationError> errors){
        return new ErrorResponse(
                errorCode.getStatus().value(),
                errorCode.name(),
                message,
                LocalDateTime.now(),
                e.getClass().getSimpleName(),
                errors
        );
    }

    public static ErrorResponse of(HttpStatus status, String message, Exception e){
        return new ErrorResponse(
                status.value(),
                status.name(),
                message,
                LocalDateTime.now(),
                e.getClass().getSimpleName(),
                List.of()
        );
    }

}