package com.zz.service;

import com.zz.pojo.User;

public interface UserService {
    User login(String username, String password);
}
