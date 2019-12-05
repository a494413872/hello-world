package com.thread.executor;

import com.thread.Lone.ThreadFuture;

import java.util.concurrent.*;

/**
 * Created by songjian on 1/7/2019.
 */
public class ScheduleThreadPool {
        public static void main(String[] args) throws InterruptedException {
            /**
             * 创建线程池
             */
            ScheduledThreadPoolExecutor poolExecutor =null;
            if (poolExecutor == null) {
                poolExecutor = new ScheduledThreadPoolExecutor(3, new ThreadPoolExecutor.CallerRunsPolicy());
            }
//            TaskRun t = new TaskRun("延迟10秒执行的任务");
//             poolExecutor.schedule(t,10,TimeUnit.SECONDS);
//
//            TaskRun t1 = new TaskRun("延迟5秒执行的任务");
//              poolExecutor.schedule(t1,5,TimeUnit.SECONDS);
//
//            TaskRun t2 = new TaskRun("延迟3秒后，每个任务加上执行之间，间隔2秒,执行一次的任务");
//            poolExecutor.scheduleAtFixedRate(t2,3,2,TimeUnit.SECONDS);
//
//            TaskRun t3 = new TaskRun("延迟1秒后，每个任务技术之后延迟2秒执行任务,延迟3秒执行一次的任务");
//            poolExecutor.scheduleAtFixedRate(t3,1,2,TimeUnit.SECONDS);


            TaskCall t4 = new TaskCall("延迟6秒后，开始执行任务");
            ScheduledFuture scheduledFuture = poolExecutor.schedule(t4,6,TimeUnit.SECONDS);
            try {
                Object o = scheduledFuture.get();
                System.out.println(o.toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }


        static class TaskRun implements Runnable {
            private String name;

            TaskRun(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                System.out.println("task " + name + " is runing");
            }
        }

    static class TaskCall implements Callable {
        private String name;

        TaskCall(String name) {
            this.name = name;
        }


        @Override
        public Object call() throws Exception {
            return "返回结果："+name;
        }
    }

}
