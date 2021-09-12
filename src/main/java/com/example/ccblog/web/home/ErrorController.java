package com.example.ccblog.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest request,HttpServletResponse reponse){
        request.setAttribute("message",reponse.getStatus());
        return "comm/error";
    }
}
