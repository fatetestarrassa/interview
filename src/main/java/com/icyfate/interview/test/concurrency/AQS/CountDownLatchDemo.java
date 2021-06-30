package com.icyfate.interview.test.concurrency.AQS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒数门闩：等待N个线程都执行完毕后才继续执行当前线程任务(换句话说子任务汇总)
 *      应用场景：和老婆、丈母娘去商场，老婆去买零食，丈母娘去买菜，这是2个线程任务，我要等她们都完成才能当苦力回家
 *               聚合打车场景，等待多个运力公司估计完毕，才返回给前端
 * @author sunbing
 * @version 1.0
 * @since 2021/6/29 15:46
 */
public class CountDownLatchDemo {
    private static final int threadCount = 6;//处理6个文件

    public static void main(String[] args) throws Exception {
//        useCountDownLatch();
        improve1();
    }

    public static void useCountDownLatch()throws Exception{
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i=0; i < threadCount; i++){
            threadPool.execute(()->{
                try {

                    System.out.println("处理文件");
                    Thread.sleep(5000);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("处理文件完毕");
                    countDownLatch.countDown();
                }

            });
        }

        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }

    /**
     * 改进方式1：通过CompletableFuture来实现
     *                  supplyAsync(有返回值)  或者  runAsync
     *
     */
    public static void improve1() throws Exception{
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });
        CompletableFuture<Void> future5 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });
        CompletableFuture<Void> future6 = CompletableFuture.runAsync(() -> {
            System.out.println("处理文件");
        });

        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(future1,future2,future3,future4,future5,future6);

//        headerFuture.join();
        System.out.println(headerFuture.get());
        System.out.println("finish");

    }

    
}
