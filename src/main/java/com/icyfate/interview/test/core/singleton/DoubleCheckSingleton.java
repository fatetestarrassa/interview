package com.icyfate.interview.test.core.singleton;

/**
 * 双重检锁模式：懒汉模式升级版，和懒汉模式相比，
 *      第一重检锁保证了大部分时候（instance被创建后）不再操作锁，提升效率
 *      第二重检锁的作用，A和B2个线程同时执行到if(instance == null)，A和B线程都会认为没有创建实例，它们会依次去执行同步代码块,
 *          然后会创建2个对象，第二重检锁if(instance == null)保证单例不被重复创建。
 *  优点：相比懒汉模式，在保证线程安全的前提下，性能大大提升。
 *  缺点：有可能被分配一个不完整的对象。具体原因如下：
 *       java指令重排的概念：JVM在不改变java语义的情况下，通过调整指令的顺序让程序运行的更快
 *       由于指令重排的存在，会导致singleton初始化和把对象地址赋给instance顺序不确定。
 *       一个线程创建单例对象时，在调用构造器之前，会先给对象分配内存地址、为对象的字段设置默认值
 *       此时，对象内存地址已经可以分配给instance变量了，然而这时对象可能还没有初始化。如果紧接着另一个线程调用getInstance方法，
 *       此时instance不为null，直接返回了一个状态不完整的单例。
 *      此问题在java1.5中得以解决，java1.5引入了volatile关键字，
 *      volatile有2个主要作用，
 *      1.内存可见 （涉及到主存 和 工作内存）
 *      2.禁止指令重排: 有很多禁止规则，其中一条是保证下一读操作在上一个写操作之后发生。
 *      volatile一个实践：就是volatile 和 CAS结合保证原子性，例如原子类，AtomicInteger
 *
 *      回归到主题：private static volatile DoubleCheckSingleton instance = null;可解决上述缺点。
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/3 16:28
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance(){
        if(instance == null){//第一重检锁
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){//第二重检锁
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }

}
