package com.thread.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songjian on 1/7/2019.
 *
 * condition实现Object的Wait和Notify功能，是配合Lock来使用的线程之间进行通信的。
 * 下面的代码就会出现两种结果，一种输出1，3,4,2. 这时候说明t1先获取锁，然后await， t2 可以唤起线程t1.
 * 另一种输出是3,4,1这时候是t2先获取锁，然后唤醒的时候没有待唤醒线程，然后释放锁，t1获取之后await就没有线程去唤醒它了。
 *
 * condition一个lock可以new多个.
 */
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock l = new ReentrantLock();
        Condition c = l.newCondition();
        Thread t1 = new Thread(()-> {
                System.out.println("1111");
                try {
                    l.lock();
                    c.await();
                    l.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("22222");
        });

        t1.start();

        Thread t2 = new Thread(()->{
                System.out.println("3333");
                l.lock();
                c.signalAll();
                l.unlock();
                System.out.println("44444");
        });
        t2.start();
    }
}
