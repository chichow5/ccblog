package com.example.ccblog.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping({"/userLogin", "/login"})
    public String toLoginPage(){
        return "comm/login";
    }
}
