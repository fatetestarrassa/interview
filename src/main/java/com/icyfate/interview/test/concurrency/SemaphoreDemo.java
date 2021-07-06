package com.icyfate.interview.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号：利用AQS的共享锁实现，state初始化为N，每来一个线程，N-1，直到state为0，线程阻塞
 *      每完成一个线程任务，N + 1,只要state  > 0,会立即执行阻塞的线程。
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/5 17:55
 */
public class SemaphoreDemo {
    private static final int count = 500;

    public static void main(String[] args) throws InterruptedException{
        ExecutorService threadPool = Executors.newFixedThreadPool(300);

        Semaphore semaphore = new Semaphore(20);
        for(int i=0;i<count;i++){
            final int threadNum = i;
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
        System.out.println("finish");

    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }
}
