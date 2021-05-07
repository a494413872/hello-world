package MemoryFence;

import java.util.concurrent.CountDownLatch;

public class TestVolatile {

    private static int x=0;
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args)
            throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = 2;
                if(x==2)
                    System.out.println("11"+(x==2) );

            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = 2;
                    System.out.println("22"+(x==2));

            }
        });
        t1.start();
        t2.start();
            countDownLatch.countDown();
//        t1.join();
//        t2.join();
    }
}
