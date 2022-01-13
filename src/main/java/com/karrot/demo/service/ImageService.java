package com.karrot.demo.service;

import com.karrot.demo.domain.image.Image;
import com.karrot.demo.domain.image.ImageRepository;
import com.karrot.demo.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class ImageService {

    private final String UPLOAD_PATH = "/img/test";

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void upload(Item item, List<MultipartFile> files){
        if (files != null){
            for (MultipartFile file : files){
                upload(item, file);
            }
        }
    }

    private void upload(Item item, MultipartFile imageFile) {
        String fileName = imageFile.getOriginalFilename();
        File targetFile = new File(UPLOAD_PATH, fileName);
        if (!new File(UPLOAD_PATH).exists()) {
            new File(UPLOAD_PATH).mkdirs();
        }

        //파일 복사
        try {
            FileCopyUtils.copy(imageFile.getBytes(), targetFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
        Image image = toEntity(targetFile, item);
        imageRepository.save(image);
    }
    private Image toEntity(File file){
        return Image.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path(file.getPath())
                .build();
    }
    private Image toEntity(File file, Item item){
        return Image.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path(file.getPath())
                .item(item)
                .build();
    }
}
