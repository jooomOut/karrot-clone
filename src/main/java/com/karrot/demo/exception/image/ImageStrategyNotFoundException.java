package com.karrot.demo.exception.image;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.BusinessException;
import com.karrot.demo.exception.common.CustomEntityNotFoundException;

public class ImageStrategyNotFoundException extends RuntimeException {
    public ImageStrategyNotFoundException(String msg) {
        super(msg);
    }
}
