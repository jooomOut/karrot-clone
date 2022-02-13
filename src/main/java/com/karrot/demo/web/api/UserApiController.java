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

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private static final String SESSION_KEY = "loginUser";

    @Autowired
    private UserService userService;
    /**
     * 회원 가입
     * @Param : RegisterUserDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity registerUser(@ModelAttribute @Valid RegisterUserDto userDto,
                                        BindingResult errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors.getAllErrors().get(0).getDefaultMessage());
        }
        log.info("someone try register");
        try{
            userService.registerUser(userDto);
        } catch(DuplicateUserException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        log.info("new user created");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity updateUserProfile(@PathVariable Long userId,
                                            @RequestPart(required = false) MultipartFile image,
                                            @RequestParam String nickname) {
        SecurityUtils.checkUser(userId);

        userService.updateProfile(userId, image, nickname);
        return ResponseEntity.ok().build();
    }
}
