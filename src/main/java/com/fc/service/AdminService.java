package com.fc.service;

import com.fc.entity.Admin;

public interface AdminService {
    Admin login(String username, String password);
}
