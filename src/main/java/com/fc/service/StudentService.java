package com.fc.service;

import com.fc.entity.Student;

import java.util.List;

public interface StudentService {
    Student login(String username, String password);

    Student selectByName(String stuName);

    int deleStu(String stuId);

    int addStudentHavePass(Student student);

    String selectIdByName(String login);

    Student selectById(String stuId);

    List<Student> getAllStudent();

    int addStudent(Student stu);

    List<Student> selectStuByStuClass(String stuClass);
}
