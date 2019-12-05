package lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songjian on 4/25/2018.
 * 计数器设置为10，每个线程让计数器减一，一直减到0，主线程才开始执行
 */
public class Lock1 {
    //重入锁
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i =0;i<10;i++){
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+"start");
                try {
                    reentrantLock.lock();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" end");
                }   finally {
                    reentrantLock.unlock();
                }
                countDownLatch.countDown();
            },"t"+i).start();
        }
        countDownLatch.await();
        System.out.println("Main Thread");
    }
}
