package com.karrot.demo.exception.item;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class InvalidItemStatusException extends BusinessException {
    public InvalidItemStatusException() {
        super(ErrorCode.INVALID_ITEM_STATUS);
    }
}
