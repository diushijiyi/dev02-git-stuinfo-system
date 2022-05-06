package com.fc.service.impl;

import com.fc.dao.AdminMapper;
import com.fc.entity.Admin;
import com.fc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        return adminMapper.selectAdminByNameAndPass(username,password);
    }
}
