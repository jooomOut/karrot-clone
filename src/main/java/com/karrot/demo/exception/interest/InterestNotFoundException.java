package com.karrot.demo.exception.interest;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class InterestNotFoundException extends BusinessException {

    public InterestNotFoundException() {
        super(ErrorCode.INTEREST_NOT_FOUND);
    }
}
