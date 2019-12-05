package com.thread.Lone;

/**
 * 第二种创建线程的方法，实现Runnable接口
 * 因为java不支持多继承，那么假设你的类本身继承了一个父类
 * 这时候还想创建线程的话，那么实现java.lang.Runnable就比较合适了。
 */
public class ThreadRunnable implements java.lang.Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"  : "+i);
        }
        System.out.println(Thread.currentThread().getName()+" end");
    }
}
