package com.karrot.demo.exception.user;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class DuplicateUserException extends BusinessException {
    public DuplicateUserException() {
        super(ErrorCode.DUPLICATE_USER);
    }
}
