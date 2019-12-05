package com.thread.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by songjian on 12/6/2018.
 */
public class ConcurrentLinkedQueueTest {


    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        new Thread(()-> {for (int i=0;i<100;i++){
                    concurrentLinkedQueue.offer(i+",");}}
         ).start();
        new Thread(()-> {for (int i=0;i<10000;i++){
            System.out.println(concurrentLinkedQueue.poll());}}
        ).start();

    }
}
