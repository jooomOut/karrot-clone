package com.karrot.demo.config;

import com.karrot.demo.domain.image.ImageType;
import com.karrot.demo.strategy.image.ImageStrategy;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {
    private final List<ImageStrategy> imageStrategies;

    @Bean
    public Map<ImageType, ImageStrategy> deleteImageByType() {
        Map<ImageType, ImageStrategy> messagesByType = new EnumMap<>(ImageType.class);
        imageStrategies.forEach(strategy -> messagesByType.put(strategy.getImageType(), strategy));
        return messagesByType;
    }

}
