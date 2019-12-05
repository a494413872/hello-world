package com.hello.spring.core.impl;

import com.hello.spring.annotation.HAutowried;
import com.hello.spring.annotation.HService;
import com.hello.spring.core.ApplicationContext;
import com.hello.spring.core.BeanFactory;
import com.hello.spring.util.Constant;
import com.hello.spring.util.StringUtil;
import com.hello.spring.web.annotation.HController;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class DefaultApplicationContext extends DefaultBeanFactory implements ApplicationContext {


    @Override
    public void loadBeanDefinationByProp(Properties contextProperteis) {
        //get scanner path
        String packageName= contextProperteis.get(Constant.SCANNER).toString();
        //扫描配置文件定义包下面所有文件。
        doScan(packageName);

    }
    private void doScan(String packageName){
        String pth = "/"+packageName.replaceAll("\\.","/").trim();
        URL url= this.getClass().getClassLoader().getResource(pth);
        if(url!=null){
            File dir = new File(url.getFile());
            for (File file : dir.listFiles()) {
                if(file.isDirectory()){
                    //如果是路径则继续遍历
                    doScan(packageName+"."+file.getName());
                }else {
                    //如果是文件就开始加载
                    registerBeanName(packageName+"."+file.getName().substring(0,file.getName().indexOf(".class")));
                }
            }
        }
    }

    @Override
    public void instanceBean() {
        //1.初始化相关类
        initBean();
        //2.自动注入
        doAutowried();
    }

    private void doAutowried() {
        if(!instancedBeanMap.isEmpty()){
            for (Map.Entry<String, Object> entry : instancedBeanMap.entrySet()) {
                String key = entry.getKey();
                Object bean = entry.getValue();
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    //如果有autowried注解，就注入。
                    if(field.isAnnotationPresent(HAutowried.class)){
                        HAutowried annotation = field.getDeclaredAnnotation(HAutowried.class);
                        String beanName = annotation.value();
                        if(StringUtil.isEmptyString(beanName)){
                            //获取属性的类型。
                            beanName = StringUtil.lowerFirstChar(field.getType().getName());
                        }
                        field.setAccessible(true);
                        try {
                            field.set(bean,instancedBeanMap.get(beanName));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        }
    }

    private void initBean() {
        if(beanClassNames.size()>0){
            try {
                for(String className:beanClassNames){
                    //根据类名通过反射获取实例。
                    Class<?> clazz = Class.forName(className);
                    //对于有service注解的类。加载进来。
                    if(clazz.isAnnotationPresent(HService.class)){
                        //默认取接口名小写字符，如果自定类名，就用自定义类名。
                        HService hService = clazz.getAnnotation(HService.class);
                        String beanName = hService.value();
                        if(StringUtil.isEmptyString(beanName)){
                            Class<?>[] interfaces = clazz.getInterfaces();
                            for (Class<?> anInterface : interfaces) {
                                beanName = StringUtil.lowerFirstChar(anInterface.getSimpleName());
                            }

                        }
                        instancedBeanMap.put(beanName,clazz.newInstance());
                    }else if(clazz.isAnnotationPresent(HController.class)){
                        //如果是controller默认使用接口名小写做为字符串
                        String beanName= StringUtil.lowerFirstChar(clazz.getSimpleName());
                        instancedBeanMap.put(beanName,clazz.newInstance());
                    }else {
                        continue;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
