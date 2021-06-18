package com.icyfate.interview.test.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 不要在foreach中使用add/remove操作，应该使用iterator
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/15 16:38
 */
public class CollectionAddRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            String temp = (String) iterator.next();
//            if(temp.equals("1")){
//                iterator.remove();
//            }
//        }

        for(String temp : list){
            if(temp.equals("2")){
                list.remove(temp);
            }
        }

        System.out.println(list);


    }
}
