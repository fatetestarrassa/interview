package com.icyfate.interview.test.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arrays.asList
 *  1.返回的是内部类，没有实现 add remove clear等方法，使用上述方法会报错
 *  2.当数组元素是基本类型时，转为list后，get(0)返回的是数组对象本身，然后才能进一步取元素。当时引用类型比如包装类，get(0)直接获取元素
 *  3.如何使用：1自己实现转list  2外边在包一层ArrayList  3.java8 stream 4.Lists.newArrayList
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/15 15:57
 */
public class ArraysTest {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List list = Arrays.asList(arr);
        arr[0] = 4;
        System.out.println(Arrays.toString(arr));
        System.out.println(list.get(0));
//        System.out.println(list.get(1));
        Integer[] arr1 = {1,2,3};
        List<Integer> mylist = Arrays.stream(arr1).collect(Collectors.toList());
        System.out.println(mylist);
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));

    }
}
