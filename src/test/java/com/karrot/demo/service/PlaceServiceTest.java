package com.karrot.demo.service;

import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.user.DuplicateUserException;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("좌표를 통한 지역 코드 얻기")
    void coordToRegionCode(){
        String x = "127.1086228";
        String y = "37.4012191";

        Assertions.assertTimeout(Duration.ofSeconds(5), () -> placeService.coordToRegionCode(x,y));

    }
}