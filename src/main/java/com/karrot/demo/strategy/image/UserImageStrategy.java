package com.karrot.demo.strategy.image;

import com.karrot.demo.domain.image.ImageType;
import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.image.UserProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserImageStrategy implements ImageStrategy {
    private final UserProfileImageRepository userProfileImageRepository;

    @Override
    public void deleteImage(Long id) {
        Optional<UserProfileImage> profileImage = userProfileImageRepository.findById(id);
        if (profileImage.isPresent()){
            UserProfileImage image = profileImage.get();

            userProfileImageRepository.delete(image);
        }
    }

    @Override
    public ImageType getImageType() {
        return ImageType.USER_PROFILE;
    }
}
