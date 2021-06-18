package com.icyfate.interview.test.core;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * 多态:父类变量指向子类示例
 *      1.变量和示例 具有集成/实现关系
 *      2.变量具体引用那个类中的方法，在运行中才能知道
 *      3.多态不能调用子类有而父类没有的方法
 *      4.如果子类重写了父类的方法，则调用子类的方法，否则调用父类的方法
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/11 14:07
 */
public class polymorphicTest {
    public static void main(String[] args) {
        A a = new B();
        a.run();
        a = new C();
        a.run();
    }
}

class A{
    public void run(){
        System.out.println("AAA");
    }
}
class B extends A{
    @Override
    public void run() {
        System.out.println("BBB");
    }

    public void test(){
        System.out.println("Test");
    }
}
class C extends A{
    @Override
    public void run() {
        System.out.println("CCC");
    }

    public void test(){
        System.out.println("Test");
    }
}

