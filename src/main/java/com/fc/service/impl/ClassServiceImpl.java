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
}
