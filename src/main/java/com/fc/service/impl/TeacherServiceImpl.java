package com.fc.service.impl;

import com.fc.dao.TeacherMapper;
import com.fc.entity.Teacher;
import com.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher login(String username, String password) {
        return teacherMapper.login(username,password);
    }

    @Override
    public String selectIdByName(String teaName) {
        return teacherMapper.selectIdByName(teaName);
    }

    @Override
    public Teacher selectById(String teaId) {
        return teacherMapper.selectByPrimaryKey(teaId);
    }

    @Override
    public int deleTea(String teaId) {
        return teacherMapper.deleteByPrimaryKey(teaId);
    }

    @Override
    public int addTeacherHavePass(Teacher teacher) {
        return teacherMapper.addTeacherHavePass(teacher);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }
}
