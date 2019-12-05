package com.thread.Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by songjian on 1/7/2019.
 * 网上例子
 * Condition 会调用LockSupport的park和unpark来阻塞和唤醒线程。
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //阻塞线程，和
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒指定线程
        LockSupport.unpark(thread);

    }
}
