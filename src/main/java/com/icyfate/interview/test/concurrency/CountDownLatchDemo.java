package com.icyfate.interview.test.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒数门闩：指定线程在N个子线程执行完之后才会执行
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/6 10:59
 */
public class CountDownLatchDemo {
    private static final int count = 500;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for(int i=0;i< count; i++){
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    test(threadnum);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");

    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }
}
