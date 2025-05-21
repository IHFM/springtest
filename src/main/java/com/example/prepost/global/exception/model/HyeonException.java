package com.example.prepost.global.exception.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HyeonException extends RuntimeException {
    private final ErrorCode errorCode;
}
