package com.example.prepost.global.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    //유효성 검사 에러
    @Builder.Default //null값 방지
    private List<FieldValidationError> fieldErrors = List.of(); //필드 초기화

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FieldValidationError{
        private String field;
        private String message;
    }
}
