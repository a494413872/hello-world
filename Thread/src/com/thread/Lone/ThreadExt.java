package com.thread.Lone;

/**
 * 创建线程的第一种方法
 * 继承java.lang.Thread，然后重写该类的run方法.
 */
public class ThreadExt extends java.lang.Thread {

        public void run(){
            System.out.println(Thread.currentThread().getName()+" start");
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"  : "+i);
            }
            System.out.println(Thread.currentThread().getName()+" end");
        }

}
