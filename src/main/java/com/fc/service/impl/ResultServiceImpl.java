package com.fc.service.impl;

import com.fc.dao.ResultMapper;
import com.fc.dao.StudentMapper;
import com.fc.entity.Rank;
import com.fc.entity.Result;
import com.fc.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultMapper resultMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Result> selectByStuId(String loginId) {
        return resultMapper.selectByStuId(loginId);
    }

    @Override
    public List<Result> getAllResult() {
        return resultMapper.getAllResult();
    }

    @Override
    public List<Result> selectResByTerm(String loginId, String resTerm) {
        return resultMapper.selectResByTerm(loginId,resTerm);
    }

    @Override
    public Result selectResultByStuIdAndSubName(String stuId, String subName) {
        return resultMapper.selectResultByStuIdAndSubName(stuId,subName);
    }

    @Override
    public int addResult(Result resultss) {
        return resultMapper.insert(resultss);
    }

    @Override
    public Result selectResultByResId(int resId) {
        return resultMapper.selectResultByResId(resId);
    }

    @Override
    public int deleteResultById(int resId) {
        return resultMapper.deleteResultById(resId);
    }

    @Override
    public List<Rank> selectRankByTerm(String resTerm) {
        List<Rank> ranks = resultMapper.selectRankByTerm(resTerm);
        for (Rank r :ranks) {
            Map<String, Integer> reamap=new HashMap<>();
            List<Map<String, Integer>> maps = resultMapper.selectResultMap(r.getStuId(), r.getResTerm());
            for (Map map:maps)
            {
                reamap.put((String)map.get("sub_name"),(Integer) map.get("res_num"));
            }
            r.setStuName(studentMapper.selectNameById(r.getStuId()));
            r.setResmap(reamap);
        }
        return ranks;
    }

    @Override
    public List<Rank> selectRankByTermAndStuClass(String resTerm, String stuClass) {
        List<Rank> ranks=resultMapper.selectRankByTermAndStuId(studentMapper.selectIdByClass(stuClass),resTerm);
        for (Rank r :ranks) {
            Map<String, Integer> reamap=new HashMap<>();
            List<Map<String, Integer>> maps = resultMapper.selectResultMap(r.getStuId(), r.getResTerm());
            for (Map map:maps)
            {
                reamap.put((String)map.get("sub_name"),(Integer) map.get("res_num"));
            }
            r.setStuName(studentMapper.selectNameById(r.getStuId()));
            r.setResmap(reamap);
        }
        return ranks;
    }
}
