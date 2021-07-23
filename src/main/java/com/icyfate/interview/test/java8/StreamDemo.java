package com.icyfate.interview.test.java8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/20 16:18
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
        //filter 中间操作  forEach是终端操作
        stringList.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

        //sorted中间操作
        stringList.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
        //并不会影响数据源
        System.out.println(stringList);

        //map 映射，将元素根绝指定的function映射成其他对象  中间操作
        stringList.stream().map(s -> s.toUpperCase()).sorted((a,b) -> b.compareTo(a)).forEach(System.out::println);

        //match 匹配，终端操作，返回boolean值
        boolean anyStartWitha = stringList.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartWitha);
        boolean allStartWitha = stringList.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(allStartWitha);
        boolean noneStartWithz = stringList.stream().noneMatch(s -> s.startsWith("z"));
        System.out.println(noneStartWithz);

        //count stream元素的个数，终端操作，返回long值
        long count = stringList.stream().filter(s -> s.startsWith("a")).count();
        System.out.println(count);

        //reduce 规约，根据一个函数把元素规约成一个对象，对象通过Optional表示  终端操作
        Optional<String> s = stringList.stream().sorted().reduce((s1,s2) -> s1 + "#" + s2);
        System.out.println(s.get());

        //特殊的reduce
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

        //并行流 和 串行流
        int max = 1000000;
        List<String> values = Lists.newArrayList();
        for(int i = 0;i < max; i++){
            values.add(UUID.randomUUID().toString());
        }

        long t0 = System.nanoTime();
        values.stream().sorted().count();
        long t1 = System.nanoTime();
        long mills = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println("串行流耗时" + mills + "毫秒");

        long t2 = System.nanoTime();
        values.parallelStream().sorted().count();
        long t3 = System.nanoTime();
        long pMills = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println("并行流耗时" + pMills + "毫秒");

    }
}
