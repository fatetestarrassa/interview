package com.icyfate.interview.test.jvm;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/6 14:46
 */
public class MemoryIntroduce {

    //线程共有区域：堆 、方法区 和 直接内存，线程私有区域：虚拟机栈、本地方法栈 和 程序计数器

    /**
     * 程序计数器：可以看做是当前线程所执行字节码的行号，通过改变它的值来选举下一条要执行的指令，从而实现流程的控制，比如循环、分支
     *      跳出、异常处理等
     * 作用：
     *      1.流程控制：通过改变它的值来选举下一条要执行的指令，从而实现流程的控制
     *      2.多线程切换，记录当前线程执行的位置，通过读取它的值来确定该线程上次执行到的位置
     *
     * 是否会内存溢出：不会出现内存溢出
     *
     */

    /**
     * 虚拟机栈：由一个个栈帧组成，每个栈帧都包括局部变量表、操作数栈、动态链接 和 方法出口信息，每一个函数的执行对应于一个栈帧进栈出栈过程
     *
     * 局部变量表主要存放编译期可知的数据类型，基本类型、对象引用（不同于对象本身，是指指向对象地址的一个指针或者一个代表对象的句柄）
     *
     * 是否会内存溢出：会出现StackOverFlowError 和 OutOfMemoryError
     *
     */

    /**
     * 本地方法栈：和虚拟机栈类似，只不过是服务于本地方法，也会创建一个栈帧，用于存放该本地方法的局部变量表、操作数栈、动态链接、出口信息
     *
     *  也会出现 StackOverFlowError 和 OutOfMemoryError 两种错误。
     */

    /**
     * 堆：最大的一块区域，主要用来存放对象实例，几乎所有的对象都在这里分配内存
     *
     * 可分为：eden区 、from survivor区、to survivor区 、 老年代 和 永久代（jdk1.8已移除，替换为元空间，也不属于堆区域）
     *
     * 大部分时候，对象在eden区分配，经过一次youngGC后，如果对象还存活，移动到survivor区，并且年龄为1，以后2个survivor区之间每次
     * 替换都会+1，直到超过年龄阈值（默认15）会移动到老年代。
     *
     * 年龄阈值：可以通过-XX:MaxTenuringThreshold 设置，但实际上移动到老年代的阈值会改变，
     *      Hotspot 遍历所有对象时，按照年龄从小到大对其所占用的大小进行累积，当累积的某个年龄大小超过了 survivor 区的一半时，
     *      取这个年龄和 MaxTenuringThreshold 中更小的一个值，作为新的晋升年龄阈值。
     *
     * 是否会内存溢出：
     *  1.OutOfMemoryError: GC Overhead Limit Exceeded ： 当 JVM 花太多时间执行垃圾回收并且只能回收很少的堆空间时，就会发生此错误。
     *  2，java.lang.OutOfMemoryError: Java heap space :假如在创建新的对象时, 堆内存中的空间不足以存放新创建的对象,
     *      就会引发java.lang.OutOfMemoryError: Java heap space 错误。
     *
     *
     */


    /**
     * 方法区：主要存储类信息、常量、静态变量。
     *
     * 方法区和永久代的区别
     *      方法区是jdk的概念，永久代是hotspot（jdk1.8之前）实现方法区的一种方式，
     *      jdk1.8以后，方法区被元空间取代。
     *
     * java.lang.OutOfMemoryError: MetaSpace  会有元空间内存溢出
     *
     */


    /************************创建对象步骤*******************************/
    /**
     * 1.类加载检查          检查类是否加载，若没有执行类加载过程
     * 2.分配内存           类加载完毕以后就已经确定了对象内存的大小
     *          分配方式
     *              1.指针碰撞      用于堆内存规整的情况，原理是使用的内存规整到一边，空闲内存规整到另一边，中间有个分界值指针，只需向着未使用内存的方向把该指针移动分配内存大小位置即可
     *                              serial 和 parNew算法
     *              2.空闲列表      用于内存不规则的情况，jvm维护一个列表，里面记录空闲内存块，分配内存时找一块足够大的内存分配即可   一般用于CMS算法
     *          并发问题：
     *              1.CAS + 失败重试
     *              2.LTAB  为每个线程在Eden区分配一个LTAB(缓冲区)，首先在LTAB中分配，如果LTAB不够大或者耗尽的时候，然后在1步骤分配
     * 3.初始化零值          属性都设为“零”值
     * 4.设置对象头          主要设置Mark Word中的hashcode、分代年龄 ，锁标志位等。
     * 5.执行init方法        属性赋值
     */

    public static void main(String[] args) {

        //String常量池：通过new就新建对象，通过""，从常量池里拿，如果有指向常量池的对象，如果常量池没有，则在常量池中创建一个，然后指针指向常量池中的对象
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = new String("abc");
        System.out.println( str1 == str2);
        System.out.println( str2 == str3);

        String str4 = str1.intern();
        System.out.println(str1 == str4);

        String s1 = "str";
        String s2 = "ing";
        String s3 = "str" + "ing";
        String s4 = "string";
        String s5 = s1 + s2;
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);

        /**
         * String s1 = new String("abcd");创建了多少个对象
         *  答案：一个或者2个，如果常量池中没有abcd，则会创建2个对象，如果有创建一个
         */
    }


}
