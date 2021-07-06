package com.icyfate.interview.test.concurrency;

import java.util.concurrent.*;

/**
 * CountDownLatch 和 CyclicBarrier 区别：
 *  CountDownLatch：一个线程等待多个线程执行完成之后才执行，强调的计数功能，只能使用一次
 *  CyclicBarrier:多个线程到达一个点后，然后再一起执行（就是说多个线程阻塞到一个点上，等到最后一个线程到达后再一起执行）
 *                可以重复利用，另外还有barrierAction功能：每个线程到达等待点就优先各自执行barrierAction方法
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/6 11:15
 */
public class CyclicBarrierDemo {
    private static final int count = 500;

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        System.out.println("------已到达5个线程");//指定线程数到达一个点之后，优先执行，然后再执行await后操作
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for(int i=0;i<count;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
                try{
                    test(threadNum);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
        System.out.println("threadNum:" + threadNum + " is ready");
        cyclicBarrier.await(60, TimeUnit.SECONDS);
        System.out.println("threadNum:" + threadNum + " finish");
    }
}
