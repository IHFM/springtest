package com.example.prepost.domain.post.exception;

import com.example.prepost.global.exception.model.ErrorCode;
import com.example.prepost.global.exception.model.GlobalException;

public class PostNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new PostNotFoundException();
    private PostNotFoundException(){
        super(ErrorCode.POST_NOT_FOUND);
    }
}
