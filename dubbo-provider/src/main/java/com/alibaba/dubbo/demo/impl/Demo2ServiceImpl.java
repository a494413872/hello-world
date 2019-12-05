package com.alibaba.dubbo.demo.impl;

import com.alibaba.dubbo.demo.Demo2Service;

import java.util.ArrayList;
import java.util.List;

public class Demo2ServiceImpl implements Demo2Service {
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }
}
