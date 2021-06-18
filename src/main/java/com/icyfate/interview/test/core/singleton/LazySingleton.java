package com.icyfate.interview.test.core.singleton;

/**
 * 懒汉模式
 *  实现原理：私有构造器及变量，只能通过静态方法获取实例，实例如果未初始化才初始化
 *  优点：需要的时候才去创建的
 *  缺点：非线程安全,在多个线程可能会并发调用它的getInstance()方法，导致创建多个实例
 *      保证线程安全的话，getInstance加上sychronized关键字，这样每次都会操作锁，效率低
 *      进一步演化到下面要讲的双重检索模式
 *  适用场景：实例使用少且线程安全场景
 * @author sunbing
 * @version 1.0
 * @since 2021/5/24 18:10
 */
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton() {
    }
    public static LazySingleton getInstance() {
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
