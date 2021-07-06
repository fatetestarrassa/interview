package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/5 15:49
 */
public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        //设置初始值
        final Integer initialRef = 0, initialStamp = 0;
        final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initialRef,initialStamp);
        System.out.println("currentValue = " + asr.getReference() + ",currentStamp=" + asr.getStamp());

        //比较替换
        final Integer newReference = 666, newStamp = 999;
        final boolean casResult = asr.compareAndSet(initialRef,newReference,initialStamp,newStamp);
        System.out.println("currentValue = " + asr.getReference() + ",currentStamp=" + asr.getStamp() + ",casReuslt=" + casResult);

        //获取最新值
        int[] arr = new int[1];
        final Integer currentValue = asr.get(arr);
        final int currStamp = arr[0];
        System.out.println("currentValue = " + currentValue + ",currentStamp=" + currStamp);

        //仅设置stamp值
        final boolean attemptStampResult = asr.attemptStamp(newReference,88);
        System.out.println("currentValue = " + asr.getReference() + ",currentStamp=" + asr.getStamp() + ",attemptStampResult=" + attemptStampResult);

        //重新设置
        asr.set(initialRef,initialStamp);
        System.out.println("currentValue = " + asr.getReference() + ",currentStamp=" + asr.getStamp());
    }
}
