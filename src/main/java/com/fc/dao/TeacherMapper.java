package com.fc.dao;

import com.fc.entity.Teacher;
import com.fc.entity.TeacherExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TeacherMapper {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(String teaId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(String teaId);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher login(@Param("teaName") String teaName, @Param("teaPass") String teaPass);

    String selectIdByName(String teaName);

    int addTeacherHavePass(Teacher teacher);

    List<Teacher> getAllTeacher();

    int addTeacher(Teacher teacher);
}