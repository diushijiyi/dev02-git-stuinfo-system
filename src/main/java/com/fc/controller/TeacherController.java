package com.fc.controller;

import com.fc.entity.MyError;
import com.fc.entity.Student;
import com.fc.entity.Teacher;
import org.springframework.ui.Model;
import com.fc.service.TeacherService;
import com.fc.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("tea")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpSession session) {
        Teacher tea=teacherService.login(username,password);
        if(tea!=null) {
            session.setAttribute("loginUser",username);
            return "redirect:/teamain.html";
        }else
        {
            map.put("msg","用户名或密码错误");
            return  "login";
        }
    }
    //返回首页
    @GetMapping( "toindex")
    public String teaToIndex(){
        return "redirect:/teamain.html";
    }
    @GetMapping("toUpdateMsgPage")
    public String toUpdateMsgPage(HttpSession httpSession, Model model){
        String login=(String) httpSession.getAttribute("loginUser");
        String loginId=teacherService.selectIdByName(login);
        Teacher tea= teacherService.selectById(loginId);
        model.addAttribute("tea",tea);
        return "tea/updatetea";
    }
    @PutMapping("updateTeaMsg")
    public String updateTeaMsg(@Valid Teacher teacher, BindingResult bindingResult, Model model, HttpSession httpSession){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if(allErrors.size()==0)
        {
            String login=(String) httpSession.getAttribute("loginUser");
            String loginId=teacherService.selectIdByName(login);
            Teacher teacherInit=teacherService.selectById(loginId);
            teacher.setTeaId(teacherInit.getTeaId());
            teacher.setTeaName(teacherInit.getTeaName());
            teacher.setTeaSex(teacherInit.getTeaSex());

            teacherService.deleTea(teacherInit.getTeaId());
            teacherService.addTeacherHavePass(teacher);
            return "redirect:/updateTeaSucc.html";
        }
        else
        {
            for (ObjectError error:allErrors)
            {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("tea",teacher);
            return "tea/updatetea";
        }
    }
}
