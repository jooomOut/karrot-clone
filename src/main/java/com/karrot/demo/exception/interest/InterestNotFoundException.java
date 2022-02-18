package com.karrot.demo.exception.interest;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.CustomEntityNotFoundException;

public class InterestNotFoundException extends CustomEntityNotFoundException {

    public InterestNotFoundException() {
        super(ErrorCode.INTEREST_NOT_FOUND);
    }
}
