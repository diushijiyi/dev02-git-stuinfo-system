package com.fc.controller;

import com.fc.entity.Admin;
import com.fc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("adm")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpSession session) {
        Admin adm=adminService.login(username,password);
        if (adm!=null){
            session.setAttribute("loginUser",username);
            return "redirect:/admmain.html";
        }else {
            map.put("msg","用户名或密码错误");
            return  "login";
        }
    }
    @GetMapping("toindex")
    public String toIndex(){
        return "redirect:/admmain.html";
    }
}
