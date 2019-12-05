package com.thread.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songjian on 11/26/2018.
 * Lock实现的是锁的功能，只是比synchronize同步块要更加强大。
 */
public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static  Object o = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                   boolean flag =  lock.tryLock();
                   if(flag){
                       System.out.println("lock:"+Thread.currentThread().getName());
                   }else {
                       System.out.println("lock:"+Thread.currentThread().getName()+" 获取锁失败");
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(lock.isLocked()){
                        lock.unlock();
                    }
                }
            },i+"");
            thread.start();
        }

        //下面使用synchronize。

//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(() -> {
//                try {
//                     synchronized (o){
//                         System.out.println("syn:"+Thread.currentThread().getName());
//                         Thread.sleep(2000);
//                     }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            },i+"");
//            thread.start();
//        }


    }
}

