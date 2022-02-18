package com.karrot.demo.exception.user;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;
import com.karrot.demo.exception.common.CustomEntityNotFoundException;

public class UserNotFoundException extends CustomEntityNotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}