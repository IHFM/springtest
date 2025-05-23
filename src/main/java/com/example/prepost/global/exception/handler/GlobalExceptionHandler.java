package com.example.prepost.global.exception.handler;

import com.example.prepost.global.exception.model.ErrorCode;
import com.example.prepost.global.exception.model.ErrorResponse;
import com.example.prepost.global.exception.model.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;
import org.springframework.context.MessageSource;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource; //국제화를 처리할 때 사용 -> 메세지 다국어 지원

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandling(GlobalException e, Locale locale){
        ErrorCode errorCode = e.getErrorCode();
        String message = messageSource.getMessage(errorCode.getMessageKey(), null, locale);
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode, message, e));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindExceptionHandling(BindException e, Locale locale){
        List<ErrorResponse.FieldValidationError> errors = e.getFieldErrors().stream()
                .map(fieldError -> new ErrorResponse.FieldValidationError(
                        fieldError.getField(),
                        messageSource.getMessage(fieldError, locale)
                ))
                .toList();
        String message = messageSource.getMessage(ErrorCode.VALIDATION_ERROR.getMessageKey(), null, locale);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(ErrorCode.VALIDATION_ERROR, message, e, errors));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e, Locale locale){
        String message = messageSource.getMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessageKey(), null, locale);
        log.error("Unexpected 예외 발생", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, message, e));
    }
}
