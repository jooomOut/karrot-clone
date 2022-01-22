package com.karrot.demo.web.dto.image;

import lombok.Builder;
import lombok.Getter;

@Builder  @Getter
public class ImageDto {
    private Long id;
    private String path;
}
