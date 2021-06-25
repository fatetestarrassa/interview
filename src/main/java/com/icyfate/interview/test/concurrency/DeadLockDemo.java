package com.icyfate.interview.test.concurrency;

/**
 * 死锁必须具备的4个条件：
 *      1.互斥条件：该资源任意一个时刻只由一个线程占用
 *      2.请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 *      3.不剥夺条件:线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 *      4.循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/25 15:04
 */
public class DeadLockDemo {

    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    /**
     * 线程1获得resource1的锁并保持，尝试获取resource2的锁
     * 线程2获得resource2的锁并保持，尝试获取resource1的锁
     * 锁只能被一个线程持有，造成互相等待，称为死锁
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {

            synchronized (resource1){
                System.out.println(Thread.currentThread() + " get rource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get reource2");
                synchronized (resource2){
                    System.out.println(Thread.currentThread() + " get resource2");
                }
            }

        },"线程 1").start();

        new Thread(() -> {

            synchronized (resource2){
                System.out.println(Thread.currentThread() + " get rource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get reource1");
                synchronized (resource1){
                    System.out.println(Thread.currentThread() + " get resource1");
                }
            }
        },"线程 2").start();

    }
}
