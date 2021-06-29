package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 自增原理：
 *      传3个参数
 *      1.this：当前对象
 *      2.offset：一个volatile修饰的属性value在对象中的偏移量
 *      3.i:增加多少
 *
 *      doWhile循环，通过1和2取得旧值，再次通过1和2取得内存实际值，如果相等自增，不相等，一直循环直到成功
 *      由于是volatile修饰，当其他线程更新了该值以后，循环体中会再次取得最新的值
 *      典型的通过cas应用
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/29 10:30
 */
public class AtomicIntegerDemo {
    private AtomicInteger count = new AtomicInteger();

    public void increment(){
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }

    public static void main(String[] args) {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        demo.increment();
        System.out.println(demo.getCount());
    }
}
