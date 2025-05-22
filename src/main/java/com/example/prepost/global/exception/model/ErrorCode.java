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
    CANNOT_MODIFY_POST(HttpStatus.FORBIDDEN, "error.cannot_modify_post"),
    CANNOT_DELETE_POST(HttpStatus.FORBIDDEN, "error.cannot_delete_post"),

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "error.validation");

    private final HttpStatus status;
    private final String messageKey;
}
