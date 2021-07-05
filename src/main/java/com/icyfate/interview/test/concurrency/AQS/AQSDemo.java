package com.icyfate.interview.test.concurrency.AQS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/29 10:49
 */
public class AQSDemo {
    public static void main(String[] args) {
        int i ;
        int k ;

        i = k = 1;
        System.out.println(i);
        System.out.println(k);


    }
}
