package com.thread.Lone;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 在这个类里面我们会分别启动不同方式创建的线程。
 */
public class TestRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //  启动继承Thread类的线程
        //  执行这段代码你可以发现console打出的log基本每次都不一样
        //  甚至有时候ext2的log会输出在ext1之前，可以看出线程启动之后，具体的执行已经和主程序无关，也不会受到其他线程的影响。
        ThreadExt ext1 = new ThreadExt();
        //这里有一个好习惯就是，对于你启动的线程最好命名一下方便排查问题。
        ext1.setName("ThreadExt one");
        ThreadExt ext2 = new ThreadExt();
        ext2.setName("ThreadExt two");
        //通过阅读源码可以发现，最终调用start()方法是去调用一个本地的start0方法
        //private native void start0(); 由于我不懂C也就不去看了。
        ext1.start();
        ext2.start();

        //下面启动实现runnable接口的线程
        //启动方法略有不同，因为runnable接口本身是没有start()方法的。
        //所以要启动方式是，新建一个Thread对象并把自己的对象当做Target传进去
        //而在Thread的run()方法的注释里面有这么写到：当构建Thread的时候target不为空，调用target的Run，而如果为空，则什么都不做。
        ThreadRunnable threadRunnable1 = new ThreadRunnable();
        //这里可以使用带名字的构造函数
        Thread t1 = new Thread(threadRunnable1,"threadRannable1 ");
        t1.start();
        //匿名方式
        new Thread(threadRunnable1,"threadRannable2 ").start();

        //下面启动实现Callable的线程
        //和实现Runnable的线程类似，最终的启动方式也是通过Thread.start来启动的。
        //但是Thread只接受Runnalbe类型的target，而我们的类实现的是callable接口,明显不匹配
        //这时候就需要引入一个FutureTask类，这个类本身实现了runnable但是持有一个callable对象。是一个典型的适配器模式，将一个Callable对象伪装成一个Runnable对象
        ThreadFuture threadFuture  = new ThreadFuture();
        //把threadFuture给传入到futureTask
        FutureTask futureTask = new FutureTask(threadFuture);
        //这时候，我们就可以把持有threadFuture对象的FutureTask当做target传给Thread对象。
        //通过源码可以看到，当start()执行的时候，会走到futureTask的Run方法，而futureTask的run方法，又回去调用ThreadFuture的call方法，并且把返回值保存到FutureTask的outcome属性上。
         new Thread(futureTask,"ThreadFuture1 ").start();
        //当我们调用get()的时候，其实会去判断当前的状态，如果线程已经跑完(state=1)，就拿到outCome给我们，而如果没有跑完就会等待跑完。
         System.out.println(futureTask.get());
        //所以如果单独调ThreadFuture相关代码的话，就可以看到不同，这里在ThreadFuture1执行完之前是不会执行ThreadFuture2的，因为get会处在等待状态,一直等到当前线程执行完成才会继续走下面的逻辑.
        //另外需要注意的是，futureTask在线程执行完之后，本身的状态已经变成Normal了，这时候再次启动同一个futureTask的时候，实际上是什么都不做的。
        // 例如：new Thread(futureTask,"ThreadFuture2 ").start(); 其实并不会走到ThreadFuture
        //想要新启动一个线程需要new一个futureTask。
        FutureTask futureTask2 = new FutureTask(threadFuture);
        new Thread(futureTask2,"ThreadFuture2 ").start();
        System.out.println(futureTask2.get());

    }
}
