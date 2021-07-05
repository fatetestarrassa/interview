package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类的ABA问题
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/5 10:25
 */
public class DefectABADemo {

    public static void main(String[] args) {
        defectOfABA();
    }

    static void defectOfABA(){
        final AtomicInteger atomicInteger = new AtomicInteger(1);

        Thread aThread = new Thread(
                () -> {
                    final int currentValue = atomicInteger.get();
                    System.out.println("线程a 第一次取值currentValue=" + currentValue);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    boolean result = atomicInteger.compareAndSet(1,2);
                    System.out.println("线程a 第二次取值currentValue=" + atomicInteger.get() + ",替换后结果=" + atomicInteger.get()  + ",替换结果=" + result);
                }
        );
        aThread.start();

        try {

            //主线程sleep，保证A线程在B线程之前跑起来
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread bThread = new Thread(
                () -> {
                    int currentValue = atomicInteger.get();
                    boolean result = atomicInteger.compareAndSet(1,2);
                    System.out.println("线程b 第一次取值currentValue=" + currentValue + ",替换后结果=" + atomicInteger.get() + ",替换结果=" + result);
                    currentValue = atomicInteger.get();
                    result = atomicInteger.compareAndSet(2,1);
                    System.out.println("线程b 第二次取值currentValue=" + currentValue + ",替换后结果=" + atomicInteger.get() + ",替换结果=" + result);
                }
        );

        bThread.start();
    }

}
