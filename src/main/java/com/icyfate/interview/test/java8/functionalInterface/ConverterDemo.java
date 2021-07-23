package com.icyfate.interview.test.java8.functionalInterface;

import java.util.Comparator;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/19 17:24
 */
public class ConverterDemo {
    public static void main(String[] args) {
        Converter<String,Integer> converter = Integer::valueOf;
        Integer i = converter.convert("123");
        System.out.println(i.getClass());

        Something something = new Something();
        Converter<String,String> converter1 = something:: startWith;
        String s = converter1.convert("jerry");
        System.out.println(s);

        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
//        num = 3;//在lambda表达式中试图修改num同样是不允许的。

        
    }
}
