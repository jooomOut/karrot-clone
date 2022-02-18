package com.karrot.demo.web.api;

import com.karrot.demo.exception.user.DuplicateUserException;
import com.karrot.demo.service.UserService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private UserService userService;
    /**
     * 회원 가입
     * @Param : RegisterUserDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity registerUser(@ModelAttribute @Valid RegisterUserDto userDto){

        userService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity updateUserProfile(@PathVariable Long userId,
                                            @RequestPart(required = false) MultipartFile image,
                                            @RequestParam @NotBlank String nickname) {
        SecurityUtils.checkUser(userId);

        userService.updateProfile(userId, image, nickname);
        return ResponseEntity.ok().build();
    }
}
