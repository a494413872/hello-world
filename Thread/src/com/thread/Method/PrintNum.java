package com.thread.Method;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNum {
    private static AtomicInteger num = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
       Thread jishu = new Thread(()->{
           lock.lock();
           while (num.intValue()<100){
               System.out.println(Thread.currentThread().getName()+" "+num.incrementAndGet());
               try {
                   condition1.await();
                   condition2.signal();

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });
       jishu.setName("奇数线程");
       jishu.start();
       Thread.sleep(100);
       Thread oushu = new Thread(()->{
           lock.lock();
           while (num.intValue()<100){
               System.out.println(Thread.currentThread().getName()+" "+num.incrementAndGet());
               try {
                   condition1.signal();
                   condition2.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       });
       oushu.setName("偶数线程");
       oushu.start();
    }
}
