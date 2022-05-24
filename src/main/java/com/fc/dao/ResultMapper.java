package com.fc.dao;

import com.fc.entity.Rank;
import com.fc.entity.Result;
import com.fc.entity.ResultExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ResultMapper {
    long countByExample(ResultExample example);

    int deleteByExample(ResultExample example);

    int deleteByPrimaryKey(Integer resId);

    int insert(Result record);

    int insertSelective(Result record);

    List<Result> selectByExample(ResultExample example);

    Result selectByPrimaryKey(Integer resId);

    int updateByExampleSelective(@Param("record") Result record, @Param("example") ResultExample example);

    int updateByExample(@Param("record") Result record, @Param("example") ResultExample example);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);

    List<Result> selectByStuId(String loginId);

    List<Result> getAllResult();

    List<Result> selectResByTerm(@Param("loginId") String loginId, @Param("resTerm") String resTerm);

    Result selectResultByStuIdAndSubName(@Param("stuId")String stuId,@Param("subName")String subName);

    Result selectResultByResId(int resId);

    int deleteResultById(int resId);

    List<Rank> selectRankByTerm(String resTerm);

    List<Map<String, Integer>> selectResultMap(@Param("stuId") String stuId,@Param("resTerm") String resTerm);

    List<Rank> selectRankByTermAndStuId(@Param("stuId") List<String> stuId,@Param("resTerm") String resTerm);
}