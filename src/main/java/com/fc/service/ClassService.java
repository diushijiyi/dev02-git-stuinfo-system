package com.fc.service;


import com.fc.entity.Class;
import org.junit.internal.Classes;

import java.util.List;

public interface ClassService {
    List<Class> getAllClass();

    Class selectByName(String className);

    Class selectById(String classId);

    int addClass(Class classes);

    int deleteById(String classId);
}
