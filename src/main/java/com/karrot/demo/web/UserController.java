package com.karrot.demo.web;

import com.karrot.demo.service.UserService;
import com.karrot.demo.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;

@Slf4j
@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        log.info("move to login");
        return "user/login";
    }

    @GetMapping("/signup")
    public String register(){
        log.info("move to register");
        return "user/register";
    }

    @GetMapping("/my-page")
    public String myPage(Model model) throws AuthenticationException {
        Long userId = SecurityUtils.getLoginUserId();
        if (userId == null){
            throw new AuthenticationException("로그인하지 않은 사용자는 접근할 수 없습니다.");
        }
        log.info("enter to mypage %l", userId);

        model.addAttribute("user", userService.findUserBy(userId));

        return "user/myPage";
    }

    @GetMapping("/my-page/edit")
    public String editProfile(Model model) throws AuthenticationException {
        Long userId = SecurityUtils.getLoginUserId();
        if (userId == null){
            throw new AuthenticationException("로그인하지 않은 사용자는 접근할 수 없습니다.");
        }
        model.addAttribute("user", userService.findUserBy(userId));

        return "user/editProfile";
    }
}
