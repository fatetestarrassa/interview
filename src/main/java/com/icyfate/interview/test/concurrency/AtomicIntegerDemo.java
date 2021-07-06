package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

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
    public static void main(String[] args) {
        int tempValue = 0;
        AtomicInteger i = new AtomicInteger(0);

        tempValue = i.getAndSet(3);//设置并返回原值
        System.out.println("tempValue = " + tempValue + ",i = " + i);
        tempValue = i.getAndIncrement();//自增1并返回原值
        System.out.println("tempValue = " + tempValue + ",i = " + i);
        tempValue = i.getAndAdd(5);//增加n并返回原值
        System.out.println("tempValue = " + tempValue + ",i = " + i);

        testAtomicIntegerArray();
    }

    public static void testAtomicIntegerArray(){
        int tempValue = 0;
        int[] arr = {1,2,4,5,3};
        AtomicIntegerArray array = new AtomicIntegerArray(arr);
        for(int i=0;i<array.length();i++){
            System.out.println(array.get(i));
        }
        tempValue = array.getAndSet(0,2);
        System.out.println("tempValue = " + tempValue + ",array = " + array);
        tempValue = array.getAndIncrement(0);
        System.out.println("tempValue = " + tempValue + ",array = " + array);
        tempValue = array.getAndAdd(0,5);
        System.out.println("tempValue = " + tempValue + ",array = " + array);

    }
}
