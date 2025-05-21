package com.example.prepost.global.exception.handler;

import com.example.prepost.global.exception.model.ErrorCode;
import com.example.prepost.global.exception.model.ErrorResponse;
import com.example.prepost.global.exception.model.HyeonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HyeonException.class)
    public ResponseEntity<ErrorResponse> hyeonExceptionHandling(HyeonException e){
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build(),
                errorCode.getStatus()
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindExceptionHandling(BindException e){
        Map<String, String> errorList = new HashMap<>();
        for(FieldError fieldError : e.getFieldErrors()){
            errorList.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}
