package com.karrot.demo.exception.user;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}