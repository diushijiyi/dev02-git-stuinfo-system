package com.fc.service;

import com.fc.entity.Result;

import java.util.List;

public interface ResultService {
    List<Result> selectByStuId(String loginId);

    List<Result> getAllResult();
}
