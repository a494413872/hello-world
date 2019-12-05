package com.hello.spring.core;

import com.hello.spring.core.impl.DefaultApplicationContext;
import com.hello.spring.util.Constant;
import com.hello.spring.util.FileTypeFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Servlet的监听器Listener
 * 只初始化一次，随web应用的停止而销毁。主要作用是： 做一些初始化的内容
 */
public class ContextLoaderListener implements ServletContextListener {

    private Properties contextProperteis = new Properties();
    private ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //loadconifg
        try {
            loadconfig();
            applicationContext = new DefaultApplicationContext();
            applicationContext.loadBeanDefinationByProp(contextProperteis);
            applicationContext.instanceBean();
            ServletContext servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute(Constant.APPLICATION_CONTEXT_KEY,applicationContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadconfig() throws IOException {
        //读取classPath下所有properties文件。
        URL classpth = this.getClass().getResource("/");
        File classPathFile = new File(classpth.getPath());
        File[] files = classPathFile.listFiles(new FileTypeFilter(".properties"));
        //遍历读取file到properties
        for (File file : files) {
            contextProperteis.load(new FileInputStream(file));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
