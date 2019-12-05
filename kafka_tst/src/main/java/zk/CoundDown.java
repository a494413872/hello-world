package zk;

import java.util.concurrent.CountDownLatch;

/**
 * Created by songjian on 9/29/2018.
 */
public class CoundDown {
    public static void main(String[] args) {
        //这个是为了让你的线程同时去掉用。
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Webservice webservice = new Webservice();
        for (int i =0; i<10;i++){
             new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("这里放一些调用webservice之前的逻辑");
                        System.out.println("比如生成一些参数之类的");
                        String param = "第"+Thread.currentThread().getName()+"个线程";
                        //然后等待在这里。
                        countDownLatch.await();
                        webservice.invoke(param);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },i+"").start();
        }
        //这里再释放掉。
        countDownLatch.countDown();
    }

}
