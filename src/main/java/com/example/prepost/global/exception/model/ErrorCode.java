package com.example.prepost.global.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal_server"),

    //게시물
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "error.post_not_found"),

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "error.validation");

    private final HttpStatus status;
    private final String messageKey;
}
