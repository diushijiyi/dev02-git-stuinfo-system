package com.fc.controller;

import com.fc.entity.*;
import com.fc.entity.Class;
import com.fc.service.ClassService;
import com.fc.service.ResultService;
import com.fc.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import com.fc.service.TeacherService;
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
    @Autowired
    private StudentService studentService;
    //教师登录
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
    //教师返回首页
    @GetMapping( "toindex")
    public String teaToIndex(){
        return "redirect:/teamain.html";
    }
    //教师信息修改
    @GetMapping("toUpdateMsgPage")
    public String toUpdateMsgPage(HttpSession httpSession, Model model){
        String login=(String) httpSession.getAttribute("loginUser");
        String loginId=teacherService.selectIdByName(login);
        Teacher tea= teacherService.selectById(loginId);
        model.addAttribute("tea",tea);
        return "tea/updatetea";
    }
    //更新教师信息
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
    @GetMapping("toteadmin/{pn}")
    public String toteadmin(@PathVariable("pn") Integer pn, Model model) {
        PageHelper.startPage(pn, 6);
        List<Result> resultsses=resultService.getAllResult();
        PageInfo<Result> page = new PageInfo<Result>(resultsses, 5);
        List<Class> classes = classService.getAllClass();
        model.addAttribute("classes",classes);
        model.addAttribute("pageInfo",page);
        return "tea/tearesultlist";
    }
    //返回成绩添加页面
    @GetMapping("resadd")
    public String toResAddPage() {
        return "tea/addResult";
    }
    //教师处理成绩添加事务
    @PostMapping("resAdd")
    public String resAdd(@Valid Result resultss,BindingResult bindingResult,Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        Result resultssVail;
        if(allErrors.size()==0)
        {
            if(studentService.selectById(resultss.getStuId())!=null) {
                resultssVail = resultService.selectResultByStuIdAndSubName(resultss.getStuId(), resultss.getSubName());
                if (resultssVail == null) {
                    resultService.addResult(resultss);
                    return "redirect:/tea/toteadmin/1";
                } else {
                    errmsg.add(new MyError("已存在该学生的此成绩信息"));
                    model.addAttribute("errors", errmsg);
                    model.addAttribute("res", resultss);
                    return "tea/addResult";
                }
            }
            else{
                errmsg.add(new MyError("不存在该学号的学生"));
                model.addAttribute("errors", errmsg);
                model.addAttribute("res", resultss);
                return "tea/addResult";
            }
        }
        else {
            for (ObjectError error:allErrors)
            {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("res",resultss);
            return "tea/addResult";
        }
    }
    //教师返回成绩修改页面
    @GetMapping("res/{resId}")
    public String updateResPage(@PathVariable("resId") int resId,Model model) {
        Result resultss=resultService.selectResultByResId(resId);
        model.addAttribute("res",resultss);
        model.addAttribute("resId",resId);
        return "tea/updateResult";
    }
    //更新成绩信息操作
    @PutMapping("res")
    public String updateRes(@Valid Result resultss,BindingResult bindingResult,Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if(allErrors.size()==0)
        {
            System.out.println(resultss);
            resultService.deleteResultById(resultss.getResId());
            resultService.addResult(resultss);
            return "redirect:/tea/toteadmin/1";
        }
        else
        {
            for (ObjectError error:allErrors)
            {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("res",resultss);
            return "tea/updateResult";
        }
    }
    //处理删除成绩信息事务
    @DeleteMapping("res/{resId}")
    public String deleres(@PathVariable("resId") int stuId) {
        resultService.deleteResultById(stuId);
        return "redirect:/tea/toteadmin/1";
    }
    //根据ID查询学生的成绩
    @GetMapping("selectById/{pn}")
    public String selectResultByStuId(@PathVariable("pn") Integer pn, @Param("stuId") String stuId, Model model) {
        PageHelper.startPage(pn, 6);
        List<Result> resultsses=resultService.selectByStuId(stuId);
        PageInfo<Result> page = new PageInfo<>(resultsses, 5);
        List<Class> classes=classService.getAllClass();
        model.addAttribute("classes",classes);
        model.addAttribute("pageInfo",page);
        model.addAttribute("stuId",stuId);
        return "tea/tearesultlistbystuid";
    }
    //返回成绩修改页面从根据学生号查询的页面发送来的
    @GetMapping("resbyid/{resId}")
    public String updateResPageById(@PathVariable("resId") int resId,Model model) {
        Result resultss=resultService.selectResultByResId(resId);
        model.addAttribute("res",resultss);
        model.addAttribute("resId",resId);
        return "tea/updateResultByid";
    }
    //更新成绩信息操作从根据学生号查询的页面发送来的
    @PutMapping("resbyid")
    public String updateResById(@Valid Result resultss,BindingResult bindingResult,Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if(allErrors.size()==0)
        {
            System.out.println(resultss);
            resultService.deleteResultById(resultss.getResId());
            resultService.addResult(resultss);
            return "redirect:/tea/selectById/1?stuId="+resultss.getStuId();
        }
        else
        {
            for (ObjectError error:allErrors)
            {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("res",resultss);
            return "tea/updateResultByid";
        }
    }
    //处理删除成绩信息事务从根据学生号查询的页面发送来的
    @DeleteMapping("resbyid/{resId}")
    public String deleresById(@PathVariable("resId") int resId) {
        Result resultss=resultService.selectResultByResId(resId);
        resultService.deleteResultById(resId);
        return "redirect:/tea/selectById/1?stuId="+resultss.getStuId();
    }
    //返回查询学生排名主页
    @GetMapping("torank")
    public String torankpage()
    {
        return "tea/rank";
    }
    //处理查询学生排名事务
    @GetMapping("selectRank")
    public String selectRank(@Param("resTerm") String resTerm, Model model) {
        List<Rank> ranks=resultService.selectRankByTerm(resTerm);
        model.addAttribute("ranks",ranks);
        model.addAttribute("resTerm",resTerm);
        return "tea/ranklist";
    }
    //返回根据班级查询学生排名主页
    @GetMapping("torankbyclass")
    public String torankbyclasspage(Model model)
    {
        return "tea/rankbyclass";
    }
    //处理根据班级查询学生排名事务
    @GetMapping("selectRankbyclass")
    public String selectRankbyclass(@Param("resTerm") String resTerm,@Param("className")String className, Model model) {
        List<MyError> errmsg = new ArrayList<>();
        if(resTerm.equals("") ||className.equals(""))
        {
            errmsg.add(new MyError("请输入学期信息以及班级信息"));
            model.addAttribute("errors", errmsg);
            return "tea/rankbyclass";
        }
        else
        {
            List<Rank> ranks=resultService.selectRankByTermAndStuClass(resTerm,className);
            model.addAttribute("ranks",ranks);
            model.addAttribute("resTerm",resTerm);
            model.addAttribute("className",className);
            return "tea/ranklistbyclass";
        }
    }
}
