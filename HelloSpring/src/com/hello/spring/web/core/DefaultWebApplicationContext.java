package com.hello.spring.web.core;

import com.hello.spring.core.ApplicationContext;
import com.hello.spring.core.impl.DefaultApplicationContext;

public class DefaultWebApplicationContext extends DefaultApplicationContext implements WebApplicationContext{
    public ApplicationContext getParentContext() {
        return parentContext;
    }

    public void setParentContext(ApplicationContext parentContext) {
        this.parentContext = parentContext;
    }

    private ApplicationContext parentContext;
}
