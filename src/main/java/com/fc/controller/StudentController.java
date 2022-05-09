package com.fc.controller;

import com.fc.entity.Class;
import com.fc.entity.MyError;
import com.fc.entity.Student;
import com.fc.service.ClassService;
import com.fc.service.StudentService;
import org.junit.internal.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stu")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpSession session)
    {
        Student stu=studentService.login(username,password);
        if(stu!=null)
        {
            session.setAttribute("loginUser",username);
            return "redirect:/stumain.html";
        }
        else
        {
            map.put("msg","用户名或密码错误");
            return  "login";
        }
    }
    @GetMapping("toindex")
    public String toIndex(){
        return "redirect:/stumain.html";
    }
    @GetMapping("toUpdateMsgPage")
    public String toUpdateMsgPage(HttpSession httpSession, Model model){
        Student stu= studentService.selectByName((String) httpSession.getAttribute("loginUser"));
        List<Class> classes=classService.getAllClass();
        model.addAttribute("stu",stu);
        model.addAttribute("class",classes);
        return "stu/updateStu";
    }
    @PostMapping("updateStuMsg")
    public String updateStuMsg(@Valid Student student, BindingResult bindingResult, Model model, HttpSession httpSession){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        List<Class> classes = classService.getAllClass();
        if(allErrors.size()==0)
        {
            Student studentInit=studentService.selectByName((String) httpSession.getAttribute("loginUser"));
            student.setStuId(studentInit.getStuId());
            student.setStuName(studentInit.getStuName());
            student.setStuClass(studentInit.getStuClass());
            student.setStuSex(studentInit.getStuSex());

            studentService.deleStu(studentInit.getStuId());
            studentService.addStudentHavePass(student);
            return "redirect:/updateSucc.html";
        }
        else
        {
            for (ObjectError error:allErrors)
            {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("stu",student);
            model.addAttribute("class",classes);
            return "stu/updateStu";
        }
    }
}
