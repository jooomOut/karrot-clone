package com.karrot.demo.exception.common;

import com.karrot.demo.exception.ErrorCode;

public class CustomEntityNotFoundException extends BusinessException{
    public CustomEntityNotFoundException(ErrorCode errorCode, String msg) {
        super(errorCode, msg);
    }

    public CustomEntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
