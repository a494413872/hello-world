package com.thread.Lone;

import java.util.concurrent.Callable;

/**
 * 前面两种方式，都没有返回值，那如果我们希望线程能把执行结果返回到主程序，就需要用到concurrent包里面的东西
 * java.util.concurrent.FutureTask 和 java.util.concurrent.Callable
 * 这里需要实现Callable接口，主要是为了实现call()方法，而call方法就是带有返回值的。
 * 启动方式见主程序。
 */
public class ThreadFuture implements Callable{
    @Override
    public Object call() throws Exception {

        System.out.println(Thread.currentThread().getName()+" start");
        int i;
        for(i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"  : "+i);
        }
        System.out.println(Thread.currentThread().getName()+" end");
        //为了体现Future的get()的等待效果，这里休息2秒
        Thread.sleep(2000);
        return Thread.currentThread().getName()+" 总共循环了"+i+"次";
    }
}
