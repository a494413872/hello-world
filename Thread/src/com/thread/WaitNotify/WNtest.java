package com.thread.WaitNotify;

/**
 * Created by songjian on 11/27/2018.
 */
public class WNtest {
    public static void main(String[] args) {
        WNtest o = new WNtest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
              synchronized (o){
                  System.out.println("1111");
                  try {
                      Thread.sleep(2000);
                      o.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println("22222");
              }
            }
        });

        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("3333");
                    o.notify();
                    System.out.println("44444");
                }
            }
        });
        t2.start();
    }
}
