package com.thread.thrackNumber;

public class ThreadContainer extends Thread {
    @Override
    public void run() {
       SomeService someService =  new SomeService();
        try {
            someService.doSome();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
