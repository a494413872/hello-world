package com.runner.impl;

import com.hello.spring.annotation.HAutowried;
import com.hello.spring.annotation.HService;
import com.runner.DepartService;
import com.runner.UserService;
@HService
public class DepartServiceImpl implements DepartService {

    @HAutowried
    private UserService userService;
    @Override
    public void sayUserName() {
        System.out.println("hello"+userService.getUserName());
    }
}
