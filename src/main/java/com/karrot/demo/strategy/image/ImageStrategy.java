package com.karrot.demo.strategy.image;

import com.karrot.demo.domain.image.ImageType;

public interface ImageStrategy {
    void deleteImage(Long id);
    ImageType getImageType();
}
