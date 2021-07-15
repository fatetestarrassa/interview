package com.icyfate.interview.test.jvm;

/**
 * 类加载过程
 *      加载
 *      连接
 *          验证
 *          准备
 *          解析
 *      初始化
 *      使用
 *      卸载
 * @author sunbing
 * @version 1.0
 * @since 2021/7/15 14:20
 */
public class ClassLoadDemo {

    /**
     *  加载
     *      1.通过类全限定名获取定义类的二进制流             可以通过jar包，war包或者其他文件方式获取
     *      2.将二进制流转为方法区的运行时数据结构
     *      3.内存中生成一个该类的Class对象实例，作为访问方法区数据结构的入口
     */


    /**
     *  验证
     *      1.文件格式验证        验证魔数是否为#CAFEBABE,主次版本号是否在虚拟机处理范围，常量池中是否有不能支持的类型
     *      2.元数据验证         是否有父类，是否有继承了不能被继承的类
     *      3.字节码验证         最复杂的阶段：通过数据流和控制流分析，程序语义是否合法、
     *      4.符号引用验证
     */

    /**
     *  准备  为类变量（静态变量）分配内存并设置初始值（“零”值）
     *      在方法区中分配，从1.7开始，字符串常量池和静态变量被移动到堆中，这是在堆中分配
     *
     */

    /**
     *  解析  将常量池中的符号引用替换为直接引用，解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用限定符 7 类符号引用进行，
     *        也就是得到类或者字段、方法在内存中的指针或者偏移量
     *      直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄
     *
     */

    /**
     *  初始化 调用<clinit>()方法(<clinit>()方法是在编译后自动生成的，并且线程安全，所以类只能被加载一次)，以下五种情况才会初始化类
     *      1.当遇到 new 、getstatic、putstatic 或invokestatic这四条字节码指令时，会初始化
     *          new 指令：创建实例对象
     *          getstatic指令：访问类的静态变量
     *          putstatic指令：给静态变量赋值
     *          invokestatic指令：调用类的静态方法
     *      2.适用java.lang.reflect包的方法进行反射调用时，如class.forName();  new Instance();
     *      3.初始化一个类，如果它的父类还未被初始化时
     *      4.虚拟机启动，用户需要指定一个要执行的主类，虚拟机会先初始化这个类
     *      5.MethodHandle 和 VarHandle 可以看作是轻量级的反射调用机制，而要想使用这 2 个调用， 就必须先使用 findStaticVarHandle 来初始化要调用的类。
     *
     */

    /**
     *  使用
     */

    /**
     *  卸载  该类的Class对象被GC
     *      卸载类需要满足3个条件。
     *      1.该类的所有对象被GC
     *      2.该类没有在其他任何地方被引用
     *      3.该类的类加载器的实例被GC
     */
}
