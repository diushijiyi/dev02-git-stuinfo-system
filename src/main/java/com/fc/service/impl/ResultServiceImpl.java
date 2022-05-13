package com.fc.service.impl;

import com.fc.dao.ResultMapper;
import com.fc.entity.Result;
import com.fc.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultMapper resultMapper;
    @Override
    public List<Result> selectByStuId(String loginId) {
        return resultMapper.selectByStuId(loginId);
    }

    @Override
    public List<Result> getAllResult() {
        return resultMapper.getAllResult();
    }
}
