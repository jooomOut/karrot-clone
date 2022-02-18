package com.karrot.demo.exception.item;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;
import com.karrot.demo.exception.common.CustomEntityNotFoundException;

public class ItemNotFoundException extends CustomEntityNotFoundException {
    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}
