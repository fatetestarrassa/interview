package com.icyfate.interview.test.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/19 16:47
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> nameList = Arrays.asList("peter","anna","park","jerry","tom");

//        Collections.sort(nameList,new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });

//        Collections.sort(nameList,(String a,String b) -> {
//            return b.compareTo(a);
//        });
//        Collections.sort(nameList, (String a,String b) -> b.compareTo(a));

        Collections.sort(nameList,(a,b) -> b.compareTo(a));

        System.out.println(nameList);
    }
}
