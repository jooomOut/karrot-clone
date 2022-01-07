package com.karrot.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class UserController {

    /*로그인 페이지로 이동*/
    @GetMapping("/main")
    public String login(){
        log.info("in Login Page");
        return "user/login/main";
    }
}
