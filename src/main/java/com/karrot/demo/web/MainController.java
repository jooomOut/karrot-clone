package com.karrot.demo.web;

import com.karrot.demo.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    /*로그인 전 랜딩 페이지로 이동*/
    @GetMapping("/main")
    public String main(){
        log.info("in main Page");
        if(SecurityUtils.getLoginUser() != null){
            return "redirect:/items";
        }
        return "user/main";
    }

    @GetMapping
    public String redirectToItems(){
        return "redirect:items";
    }
}
