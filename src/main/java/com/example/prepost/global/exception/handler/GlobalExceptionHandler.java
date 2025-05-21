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

import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource; //국제화를 처리할 때 사용 -> 메세지 다국어 지원

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandling(GlobalException e, Locale locale){
        ErrorCode errorCode = e.getErrorCode();
        String message = messageSource.getMessage(errorCode.getMessageKey(), null, locale);
        return ResponseEntity.status(errorCode.getStatus()).body(
                ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .error(errorCode.getStatus().getReasonPhrase())
                        .code(errorCode.name())
                        .message(message)
                        .build()
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindExceptionHandling(BindException e, Locale locale){
        List<ErrorResponse.FieldValidationError> errors = e.getFieldErrors().stream()
                .map(fieldError -> ErrorResponse.FieldValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .toList();
        String message = messageSource.getMessage(ErrorCode.VALIDATION_ERROR.getMessageKey(), null, locale);
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .code(ErrorCode.VALIDATION_ERROR.name())
                        .message(message)
                        .fieldErrors(errors)
                        .build()
        );
    }
    //TODO: handle other exceptions
}
