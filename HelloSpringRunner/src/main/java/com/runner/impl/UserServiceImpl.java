package com.runner.impl;

import com.hello.spring.annotation.HService;
import com.runner.UserService;

@HService
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName() {
        return "wangcai";
    }
}
