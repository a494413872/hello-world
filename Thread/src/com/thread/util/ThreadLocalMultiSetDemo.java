package com.thread.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalMultiSetDemo {
    private static ThreadLocal<String> sdf = new ThreadLocal<>();
    private static ThreadLocal<String> sdf2 = new ThreadLocal<>();
    public static void main(String[] args) {
         sdf.set("1");
        sdf2.set("2");
        System.out.println(sdf.get());
        System.out.println(sdf2.get());
    }


}
