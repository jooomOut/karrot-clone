package com.karrot.demo.service;

import com.karrot.demo.domain.image.ItemImageRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @InjectMocks
    private ImageService imageService;

    @Mock
    private ItemImageRepository imageRepository;




    @Test
    @DisplayName("이미지 업로드")
    void uploadImage() throws IOException {
        String path = new ClassPathResource("/static/img").getFile().getAbsolutePath();
        MockMultipartFile multipartFile = new MockMultipartFile(
                "karrot.png", new FileInputStream(new File(path + "/karrot.png"))
        );

        when(imageRepository.save(any()))
                .thenReturn(null);
        imageService.upload((Item)null, multipartFile);
    }


}