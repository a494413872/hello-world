package com.hello.spring.web.servlet;

import com.hello.spring.core.ApplicationContext;
import com.hello.spring.util.Constant;
import com.hello.spring.web.core.DefaultWebApplicationContext;
import com.hello.spring.web.core.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloDispatcherServlet extends HttpServlet {

    private WebApplicationContext webApplicationContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //获取webapplicationContext
        initWebApplicationContext(config);
        //初始化servletMapping
        initHandlerMapping();
    }

    private void initWebApplicationContext(ServletConfig config) {
        //从servletcontext获取applicationcontext
        ApplicationContext applicationContext = (ApplicationContext) config.getServletContext().getAttribute(Constant.APPLICATION_CONTEXT_KEY);
        webApplicationContext = new DefaultWebApplicationContext();
        ((DefaultWebApplicationContext) webApplicationContext).setParentContext(applicationContext);
        //省略获取失败逻辑，获取不到初始化逻辑，忽略webApplicationContext和applicationContext不同
        //把当前webapplicationContext放入servletcontext，方便获取
        config.getServletContext().setAttribute(Constant.APPLICATION_CONTEXT_KEY,applicationContext);
    }

    private void initHandlerMapping() {
    }
}
