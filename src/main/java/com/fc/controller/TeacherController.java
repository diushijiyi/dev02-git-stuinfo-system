package com.fc.controller;

import com.fc.entity.Class;
import com.fc.entity.MyError;
import com.fc.entity.Result;
import com.fc.entity.Student;
import com.fc.entity.Teacher;
import com.fc.service.ClassService;
import com.fc.service.ResultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.internal.Classes;
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
    @Autowired
    private ResultService resultService;
    @Autowired
    private ClassService classService;
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
    @PutMapping ("updateTeaMsg")
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
    //返回成绩管理首页
    @GetMapping(value = "/toteadmin/{pn}")
    public String toteadmin(@PathVariable("pn") Integer pn, Model model)
    {
        PageHelper.startPage(pn, 6);
        List<Result> resultsses=resultService.getAllResult();
        PageInfo<Result> page = new PageInfo<Result>(resultsses, 5);
        List<Class> classes = classService.getAllClass();
        model.addAttribute("classes",classes);
        model.addAttribute("pageInfo",page);
        return "tea/tearesultlist";
    }
}
