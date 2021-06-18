package com.icyfate.interview.test.core.singleton;

/**
 * 静态内部类模式：恶汉模式升级版,同样利用类只加载一次的原理保证只有一个实例，不同之处在于
 *      在内部类中创建实例，只有使用内部类的时候才会加载（不同于恶汉模式，上来就会加载类）
 *      实现了延时加载和线程安全
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/3 17:03
 */
public class StaticInnerClassSingleton {

    private static class SingletonHolder{
        public static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
    }

    public static StaticInnerClassSingleton getInstance(){
        return SingletonHolder.instance;
    }

}
