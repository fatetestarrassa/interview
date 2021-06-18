package com.icyfate.interview.test.core;

/**
 * java的值传递：
 *      1.基本类型的值：字面量
 *      2.引用类型的值：堆中对象的引用
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/11 10:36
 */
public class ValuePropagation {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        swap(num1,num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);


        int[] arr = {1,2,3,4};
        System.out.println(arr[0]);
        swapArr(arr);
        System.out.println(arr[0]);
    }

    private static void swapArr(int[] array) {
        array[0] = 0;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
