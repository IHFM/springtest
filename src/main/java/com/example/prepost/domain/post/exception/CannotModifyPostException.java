package com.example.prepost.domain.post.exception;

import com.example.prepost.global.exception.model.ErrorCode;
import com.example.prepost.global.exception.model.GlobalException;

public class CannotModifyPostException extends GlobalException {
    public static final GlobalException EXCEPTION = new CannotModifyPostException();
    public CannotModifyPostException() {
        super(ErrorCode.VALIDATION_ERROR);
    }
}
