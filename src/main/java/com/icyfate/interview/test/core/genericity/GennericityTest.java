package com.icyfate.interview.test.core.genericity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * java中泛型
 *  类型擦除机制：编译后，生成的java字节码中不存在泛型信息的，也就是说使用泛型的类型参数，编译后被去掉，这个过程称为类型擦除
 *  List<Object> 和 List<String>在编译后都会变成List，JVM中看到的只是List
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/10 14:13
 */
public class GennericityTest {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(12);

        //list.add("a");泛型检查会报错
        //编译后会进行类型擦除，jvm中只看到list，通过反射可以添加其他类型元素
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list,"aa");
        System.out.println(list);

        Integer[] intArray = {1,2,3};
        String[] stringArray = {"one","two","three"};
        printArray(intArray);
        printArray(stringArray);


    }

    /**
     * 方法泛型
     *
     */
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}
