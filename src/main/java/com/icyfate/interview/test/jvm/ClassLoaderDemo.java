package com.icyfate.interview.test.jvm;

/**
 * BootstrapClassLoader(启动类加载器) ：最顶层的类加载器，由C++实现，负责加载%JAVA_HOME%/lib目录下的jar包
 * ExtensionClassLoader(扩展类加载器)：java实现，负责加载%JRE_HOME%/lib/ext目录下的jar包
 * AppClassLoader(应用程序类加载器)：jvm实现，负责加载当前应用classpath下所有jar包和类
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/16 16:11
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent());


    }
}
