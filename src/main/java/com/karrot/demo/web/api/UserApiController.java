package com.karrot.demo.web.api;

import com.karrot.demo.web.dto.user.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserApiController {

    /**
     * 회원 가입
     * @Param : RegisterUserDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity registerUser(@ModelAttribute RegisterUserDto user){
        log.info("someone try register");

        return ResponseEntity.ok().build();
    }


}
