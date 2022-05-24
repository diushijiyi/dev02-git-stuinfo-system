package com.fc.service.impl;

import com.fc.dao.StudentMapper;
import com.fc.entity.Student;
import com.fc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student login(String username, String password) {
        return studentMapper.login(username,password);
    }

    @Override
    public Student selectByName(String stuName) {
        return studentMapper.selectByName(stuName);
    }

    @Override
    public int deleStu(String stuId) {
        return studentMapper.deleteByPrimaryKey(stuId);
    }

    @Override
    public int addStudentHavePass(Student student) {
        return studentMapper.insertStudentHavaPass(student);
    }

    @Override
    public String selectIdByName(String login) {
        return studentMapper.selectIdByName(login);
    }

    @Override
    public Student selectById(String stuId) {
        return studentMapper.selectById(stuId);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public int addStudent(Student stu) {
        return studentMapper.addStudent(stu);
    }

    @Override
    public List<Student> selectStuByStuClass(String stuClass) {
        return studentMapper.selectStuByStuClass(stuClass);
    }

}
