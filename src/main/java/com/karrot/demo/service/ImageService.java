package com.karrot.demo.service;

import com.karrot.demo.domain.image.*;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.strategy.image.ImageContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final String STATIC_PATH = "/static/img";
    private final String ITEM_PATH = "/items";
    private final String USER_PROFILE_PATH = "/user_profile";

    private final ItemImageRepository itemImageRepository;
    private final UserProfileImageRepository userProfileImageRepository;
    private final ImageContext imageContext;

    @Transactional(rollbackOn = IOException.class)
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
        itemImageRepository.save(itemImage);
    }

    @Transactional
    public void upload(Account account, MultipartFile image) throws IOException {
        if (image == null) return;
        if (account.getImage() != null) {
            userProfileImageRepository.delete(account.getImage());
        }
        File targetFile = storeImageFile(image, USER_PROFILE_PATH);

        UserProfileImage profileImage = toEntity(targetFile, account);
        userProfileImageRepository.save(profileImage);
    }

    private File storeImageFile(MultipartFile image, String postfixPath) throws IOException {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String path = new ClassPathResource(STATIC_PATH).getFile().getAbsolutePath() + postfixPath;

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

    /*switch (type) {
            case "user_profile" : deleteUserProfileImage(imageId); break;
            case "item" : deleteItemImage(imageId); break;
        }*/
    @Transactional
    public void deleteImage(Long imageId, ImageType type){
        imageContext.deleteImage(imageId,type);
    }


    private ItemImage toEntity(File file, Item item){
        return ItemImage.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path("/img" + ITEM_PATH + "/" + file.getName())
                .item(item)
                .build();
    }
    private UserProfileImage toEntity(File file, Account user){
        return UserProfileImage.builder()
                .fileName(file.getName())
                .size(file.length()) // byte size
                .path("/img" + USER_PROFILE_PATH + "/" + file.getName())
                .user(user)
                .build();
    }
}
