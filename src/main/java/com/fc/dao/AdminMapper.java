package com.fc.dao;

import com.fc.entity.Admin;
import com.fc.entity.AdminExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(String adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(String adminId);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectAdminByNameAndPass(@Param("admName") String admName, @Param("admPass") String admPass);
}