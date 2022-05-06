package com.fc.service;

import com.fc.entity.Student;

public interface StudentService {
    Student login(String username, String password);

    Student selectByName(String stuName);

    int deleStu(String stuId);

    int addStudentHavePass(Student student);
}
