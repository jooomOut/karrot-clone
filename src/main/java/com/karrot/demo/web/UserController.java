package com.karrot.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class UserController {

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
}
