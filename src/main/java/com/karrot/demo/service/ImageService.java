package com.karrot.demo.service;

import com.karrot.demo.domain.image.ItemImage;
import com.karrot.demo.domain.image.ItemImageRepository;
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
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {
    private final String STATIC_PATH = "/static/img";
    private final String ITEM_PATH = "/items";
    private final String USER_PROFILE_PATH = "/user_profile";

    private ItemImageRepository itemImageRepository;
    private UserProfileImageRepository userProfileImageRepository;

    @Autowired
    public ImageService(ItemImageRepository itemImageRepository, UserProfileImageRepository userProfileImageRepository) {
        this.itemImageRepository = itemImageRepository;
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
        itemImageRepository.save(itemImage);
    }

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
    public void deleteImage(Long imageId, String type){
        switch (type) {
            case "user_profile" : deleteUserProfileImage(imageId); break;
            case "item" : deleteItemImage(imageId); break;
        }
    }

    private void deleteItemImage(Long imageId) {
        Optional<ItemImage> optionalItemImage = itemImageRepository.findById(imageId);
        if (optionalItemImage.isPresent()){
            ItemImage itemImage = optionalItemImage.get();
            deleteFile("/static" + itemImage.getPath());
            itemImageRepository.delete(itemImage);
        }
    }

    public void deleteUserProfileImage(Long imageId){
        Optional<UserProfileImage> profileImage = userProfileImageRepository.findById(imageId);
        if (profileImage.isPresent()){
            UserProfileImage image = profileImage.get();

            userProfileImageRepository.delete(image);
        }
    }

    private void deleteFile(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()){
            if (file.delete()){
                log.info("파일 삭제 완료 - " + path);
            }
        }
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
