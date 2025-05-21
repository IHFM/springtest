package com.example.prepost.global.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러 펑"),

    //게시물
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post Not Found");

    private final HttpStatus status;
    private final String message;
}
