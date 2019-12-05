package com.hello.spring.core.impl;

import com.hello.spring.core.BeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {
     List<String> beanClassNames;
     Map<String,Object> instancedBeanMap;

    DefaultBeanFactory(){
        beanClassNames = new ArrayList<>();
        instancedBeanMap = new HashMap<>();
    }

    @Override
    public Object getBean(String name) {
        return instancedBeanMap.get(name);
    }

    @Override
    public void registerBeanName(String beanName) {
        beanClassNames.add(beanName);
    }


}
