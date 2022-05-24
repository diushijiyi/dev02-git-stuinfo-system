package com.fc.service;

import com.fc.entity.Rank;
import com.fc.entity.Result;

import java.util.List;

public interface ResultService {
    List<Result> selectByStuId(String loginId);

    List<Result> getAllResult();


    List<Result> selectResByTerm(String loginId, String resTerm);

    Result selectResultByStuIdAndSubName(String stuId, String subName);

    int addResult(Result resultss);

    Result selectResultByResId(int resId);

    int deleteResultById(int resId);

    List<Rank> selectRankByTerm(String resTerm);

    List<Rank> selectRankByTermAndStuClass(String resTerm, String stuClass);
}
