package com.karrot.demo.service;

import com.karrot.demo.domain.image.ItemImageRepository;
import com.karrot.demo.domain.image.ItemImage;
import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.image.UserProfileImageRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {

    private final String ITEM_PATH = "/items";
    private final String USER_PROFILE_PATH = "/user_profile";

    private ItemImageRepository imageImageRepository;
    private UserProfileImageRepository userProfileImageRepository;

    @Autowired
    public ImageService(ItemImageRepository imageImageRepository, UserProfileImageRepository userProfileImageRepository) {
        this.imageImageRepository = imageImageRepository;
        this.userProfileImageRepository = userProfileImageRepository;
    }

    public void upload(Item item, List<MultipartFile> files) {
        if (files != null){
            for (MultipartFile file : files){
                try {
                    upload(item, file);
                } catch (IOException e){
                    log.info("Can not upload file");
                }
            }
        }
    }

    public void upload(Item item, MultipartFile image) throws IOException {
        if (image == null) return;

        File targetFile = storeImageFile(image, ITEM_PATH);

        ItemImage itemImage = toEntity(targetFile, item);
        imageImageRepository.save(itemImage);
    }

    public void upload(Account account, MultipartFile image) throws IOException {
        if (image == null) return;

        File targetFile = storeImageFile(image, USER_PROFILE_PATH);

        UserProfileImage profileImage = toEntity(targetFile, account);
        userProfileImageRepository.save(profileImage);
    }

    private File storeImageFile(MultipartFile image, String postfixPath) throws IOException {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String path = new ClassPathResource("/static/img").getFile().getAbsolutePath() + postfixPath;

        File targetFile = new File(path, fileName);
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        //파일 복사
        try {
            FileCopyUtils.copy(image.getBytes(), targetFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    private ItemImage toEntity(File file){
        return ItemImage.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path(file.getPath())
                .build();
    }
    private ItemImage toEntity(File file, Item item){
        return ItemImage.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path(file.getPath())
                .item(item)
                .build();
    }
    private UserProfileImage toEntity(File file, Account user){
        return UserProfileImage.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path(file.getPath())
                .user(user)
                .build();
    }
}
