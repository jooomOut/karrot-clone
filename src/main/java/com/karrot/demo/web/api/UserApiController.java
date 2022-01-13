package com.karrot.demo.web.api;

import com.karrot.demo.exception.DuplicateUserException;
import com.karrot.demo.service.UserService;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import com.karrot.demo.web.dto.user.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/users")
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
    public ResponseEntity registerUser(@ModelAttribute RegisterUserDto userDto){
        log.info("someone try register");
        try{
            userService.registerUser(userDto);
        } catch(DuplicateUserException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
    /*@PostMapping("/login")
    public ResponseEntity login(@Param("email") String email,
                                @Param("password") String password,
                                HttpServletRequest request){
            //로그인 시도
        UserSessionDto result = userService.login(email, password);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_KEY, result);

        return ResponseEntity.ok().build();
    }*/

}
