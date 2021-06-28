package com.icyfate.interview.test.concurrency.ThreadPool;

import java.util.Date;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/28 19:13
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return this.command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " startTime = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " endTime = " + new Date());
    }

    private void processCommand(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
