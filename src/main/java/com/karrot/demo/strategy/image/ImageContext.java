package com.karrot.demo.strategy.image;

import com.karrot.demo.domain.image.ImageType;
import com.karrot.demo.exception.image.ImageStrategyNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
@Slf4j
public class ImageContext {
    private final Map<ImageType, ImageStrategy> imageStrategyMap;

    public void deleteImage(Long id, ImageType imageType) throws ImageStrategyNotFoundException {
        ImageStrategy imageStrategy = imageStrategyMap.getOrDefault(imageType, null);
        if (Objects.isNull(imageStrategy)) {
            throw new ImageStrategyNotFoundException("Notification Type not found. type: " + imageType);
        }
        imageStrategy.deleteImage(id);
    }

}
