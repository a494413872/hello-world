package com.thread.Lock.trylock;

import java.util.concurrent.locks.ReentrantLock;

public class SomeImpl {

    private static SomeImpl someimpl;

    private static ReentrantLock lock = new ReentrantLock();

    private SomeImpl(){

    }

    public static SomeImpl getInstance(){
        if(someimpl==null){
            synchronized (SomeImpl.class){
                if(someimpl==null){
                    someimpl = new SomeImpl();
                }
            }
        }
        return someimpl;
    }

    public void doSome() {

            boolean flag = lock.tryLock();
            if(flag){
                try {
                System.out.println(Thread.currentThread().getName()+" start");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+" end");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            }else {
                System.out.println(Thread.currentThread().getName()+" get lock fail");
            }

    }
}
