package com.thread.Lock.trylock;

public class TryLockTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                SomeImpl.getInstance().doSome();
            },i+"");
            thread.start();
        }
    }
}
