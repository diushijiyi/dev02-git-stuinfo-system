package com.fc.dao;

import com.fc.entity.Student;
import com.fc.entity.StudentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(String stuId);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(String stuId);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student login(@Param("stuName") String stuName, @Param("stuPass") String stuPass);

    Student selectByName(String stuName);

    int insertStudentHavaPass(Student student);

    String selectIdByName(String login);

    Student selectById(String stuId);

    String selectNameById(String stuId);

    List<String> selectIdByClass(String stuClass);

    List<Student> getAllStudent();

    List<Student> selectStuByStuClass(String stuClass);

    int addStudent(Student stu);
}