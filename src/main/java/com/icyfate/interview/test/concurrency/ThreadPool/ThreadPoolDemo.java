package com.icyfate.interview.test.concurrency.ThreadPool;

import java.util.concurrent.*;

/**
 * 线程池好处：
 *      1.降低资源消耗            不用每次都去创建线程
 *      2.提高响应速度            有任务执行时，直接执行，省去创建线程的消耗
 *      3.提高线程的可管理型
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/28 15:12
 */
public class ThreadPoolDemo {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i = 0; i < 10; i++){
            Runnable worker = new MyRunnable("");
            executor.execute(worker);
        }

        executor.shutdown();
        while (executor.isTerminated()){

        }

        System.out.println("所有线程执行结束");
    }


}
