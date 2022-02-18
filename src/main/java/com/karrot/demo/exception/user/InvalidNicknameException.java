package com.karrot.demo.exception.user;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class InvalidNicknameException extends BusinessException {
    public InvalidNicknameException() {
        super(ErrorCode.INVALID_NICKNAME);
    }
}
