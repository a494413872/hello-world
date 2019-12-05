package com.hello.spring.core;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanName(String BeanName);
}
