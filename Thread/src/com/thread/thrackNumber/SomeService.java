package com.thread.thrackNumber;

public class SomeService {
    public void doSome() throws InterruptedException {
        Log4JLog.logInfo("start doing somthion");
        //随机睡眠1秒到5秒之间
        //模拟线程的不确定性
        Thread.sleep((long) (Math.random()*5000));
        //这样你就完全看不出来哪个end和哪个start属于同一个线程之间的逻辑
        Log4JLog.logInfo("doing somthin");
        Thread.sleep((long) (Math.random()*5000));
        Log4JLog.logInfo("end");

        //下面是同样的逻辑。但是加上trackNumber这样你就能区分哪些日志属于哪一个线程
        //一定先初始化
        LogTrackContext.initTrackNumber();
        //相同逻辑
        FawneLog.logInfo("start doing somthion");
        //随机睡眠1秒到5秒之间
        //模拟线程的不确定性
        Thread.sleep((long) (Math.random()*5000));
        //这样你就完全看不出来哪个end和哪个start属于同一个线程之间的逻辑
        FawneLog.logInfo("doing somthin");
        Thread.sleep((long) (Math.random()*5000));
        FawneLog.logInfo("end");


    }
}
