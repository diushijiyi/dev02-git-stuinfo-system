package com.fc.service.impl;

import com.fc.dao.ClassMapper;
import com.fc.entity.Class;
import com.fc.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
    @Override
    public List<Class> getAllClass() {
        return classMapper.getAllClass();
    }

    @Override
    public Class selectByName(String className) {
        return classMapper.selectClassByName(className);
    }

    @Override
    public Class selectById(String classId) {
        return classMapper.selectById(classId);
    }

    @Override
    public int addClass(Class classes) {
        return classMapper.addClass(classes);
    }

    @Override
    public int deleteById(String classId) {
        return classMapper.deleteById(classId);
    }
}
