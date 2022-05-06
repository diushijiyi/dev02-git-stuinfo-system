package com.fc.service;

import com.fc.entity.Teacher;

public interface TeacherService {
    Teacher login(String username, String password);

    String selectIdByName(String teaName);

    Teacher selectById(String teaId);

    int deleTea(String teaId);

    int addTeacherHavePass(Teacher teacher);
}
