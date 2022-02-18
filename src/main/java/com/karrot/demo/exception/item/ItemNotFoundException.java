package com.karrot.demo.exception.item;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}
