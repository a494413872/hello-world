package com.singleton;

public class SignletonHigh {
    /**
     * 用volatile的原因是防止指令重排。
     * 因为signletonHigh = new SignletonHigh(); 不是一个原子性的操作。
     * 如果指令重排，先完成了复制，对象还没有初始化完成的话，另外一个线程就会拿到一个未初始化完成的对象。
     */
    private static volatile SignletonHigh signletonHigh;

    private SignletonHigh(){}

    /**
     * 双重检查
     * @return
     */
    public static SignletonHigh getInstance(){
        /**
         * 第一重检查，是为了当单例存在的时候，不需要去锁。提高性能，否则所有的逻辑都获取锁的逻辑。
         * 第二重检查，是为了防止重复创建对象。如果两个线程同时通过第一个if，没有第二个if的话，就会重复创建对象。
         */
        if(signletonHigh==null){
            synchronized (SignletonHigh.class){
                if(signletonHigh==null){
                    signletonHigh = new SignletonHigh();
                }
            }

        }
        return signletonHigh;
    }
}
