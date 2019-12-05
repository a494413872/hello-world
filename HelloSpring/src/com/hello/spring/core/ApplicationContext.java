package com.hello.spring.core;

import java.util.Properties;

public interface ApplicationContext extends BeanFactory {
    void loadBeanDefinationByProp(Properties contextProperteis);
    void instanceBean();
}
