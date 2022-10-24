package com.karrot.demo.web.api;

import com.karrot.demo.domain.image.ImageType;
import com.karrot.demo.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/images")
public class ImageApiController {

    private ImageService imageService;
    @Autowired
    public ImageApiController(ImageService imageService) {
        this.imageService = imageService;
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity deleteImage(@PathVariable Long imageId,
                                      @RequestParam ImageType type) {
        log.info("delete image - id : " + imageId);
        imageService.deleteImage(imageId, type);
        return ResponseEntity.ok().build();
    }
}
