package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redircet:/index.html";
    }

}
