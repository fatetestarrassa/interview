package com.icyfate.interview.test.concurrency;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * ThreadLocal使用
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/25 17:38
 */
public class ThreadLocalDemo implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        for(int i = 0;i < 10;i++){
            Thread t = new Thread(demo, "" + i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + " default formatter :" + formatter.get().toPattern());

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        formatter.set(new SimpleDateFormat());

        System.out.println("线程" + Thread.currentThread().getName() + " formatter :" + formatter.get().toPattern());
    }
}
