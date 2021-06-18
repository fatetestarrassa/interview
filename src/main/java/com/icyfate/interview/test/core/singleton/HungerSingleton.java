package com.icyfate.interview.test.core.singleton;

/**
 * 饿汉模式
 *  实现原理：利用类加载原理，静态变量在类加载的时候被实例化，类只加载一次，故保证只有一个示例
 *  优点：类加载创建实例，不存在多个线程创建多个实例的情况，线程安全
 *  缺点：没有被用到的时候也会因加载类被实例化，而且在类加载之前被实例化，浪费内存
 *  适用场景：单例占内存较小并且初始化时就会被用到的情况（占内存大不适合，某个特定场景才会被用到也不太适合）
 * @author sunbing
 * @version 1.0
 * @since 2021/5/24 17:58
 */
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();
    private HungerSingleton(){
    }
    public static HungerSingleton getInstance(){
        return instance;
    }

}
