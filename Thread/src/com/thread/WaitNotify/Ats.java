package com.thread.WaitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songjian on 11/27/2018.
 */
public class Ats {
    public static void main(String[] args) {

        Lock l = new ReentrantLock();
        Condition c = l.newCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println("1111");
                try {
                    l.lock();
                    c.await();
                    l.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("22222");
            }
        });

        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println("3333");
                    l.lock();
                    c.signalAll();
                    l.unlock();
                    System.out.println("44444");
            }
        });
        t2.start();
    }
}
