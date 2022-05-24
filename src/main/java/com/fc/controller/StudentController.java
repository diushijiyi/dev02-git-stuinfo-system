package com.fc.controller;

import com.fc.entity.Class;
import com.fc.entity.MyError;
import com.fc.entity.Result;
import com.fc.entity.Student;
import com.fc.service.ClassService;
import com.fc.service.ResultService;
import com.fc.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
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
    @Autowired
    private ResultService resultService;
    //学生登录
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpSession session) {
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
    //学生返回首页
    @GetMapping("toindex")
    public String toIndex(){
        return "redirect:/stumain.html";
    }
    //学生信息修改
    @GetMapping("toUpdateMsgPage")
    public String toUpdateMsgPage(HttpSession httpSession, Model model){
        Student stu= studentService.selectByName((String) httpSession.getAttribute("loginUser"));
        List<Class> classes=classService.getAllClass();
        model.addAttribute("stu",stu);
        model.addAttribute("class",classes);
        return "stu/updateStu";
    }
    //更新学生信息
    @PutMapping("updateStuMsg")
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
    //返回学生成绩首页
    @GetMapping("toresdmin/{pn}")
    public String toresdmin(@PathVariable("pn") Integer pn,Model model,HttpSession httpSession) {
        String login=(String) httpSession.getAttribute("loginUser");
        String loginId=studentService.selectIdByName(login);
        PageHelper.startPage(pn, 9);
        List<Result> resultsses = resultService.selectByStuId(loginId);
        PageInfo<Result> page = new PageInfo<Result>(resultsses, 5);
        model.addAttribute("pageInfo",page);
        return "stu/resultlist";
    }
    //学生根据学期成绩查询
    @GetMapping("selectResByTerm/{pn}")
    public String selectResByTerm(@PathVariable("pn") Integer pn,Model model,HttpSession session,@Param("resTerm") String resTerm){
        PageHelper.startPage(pn, 9);
        String login=(String) session.getAttribute("loginUser");
        String loginId=studentService.selectIdByName(login);
        List<Result> results=resultService.selectResByTerm(loginId,resTerm);
        PageInfo<Result> page = new PageInfo<Result>(results, 5);
        model.addAttribute("pageInfo",page);
        return "stu/reslistbyterm";
    }
}
