package com.karrot.demo.strategy.image;

import com.karrot.demo.domain.image.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemImageStrategy implements ImageStrategy {
    private final ItemImageRepository itemImageRepository;

    @Override
    public void deleteImage(Long id) {
        Optional<ItemImage> optionalItemImage = itemImageRepository.findById(id);
        if (optionalItemImage.isPresent()){
            ItemImage itemImage = optionalItemImage.get();
            deleteFile("/static" + itemImage.getPath());
            itemImageRepository.delete(itemImage);
        }
    }

    @Override
    public ImageType getImageType() {
        return ImageType.ITEM;
    }

    private void deleteFile(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()){
            if (file.delete()){
                log.info("파일 삭제 완료 - " + path);
            }
        }
    }
}
