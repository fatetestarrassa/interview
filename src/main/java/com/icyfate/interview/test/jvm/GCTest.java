package com.icyfate.interview.test.jvm;

/**
 * 设置执行参数，打印GC日志
 *  -XX:+PrintGCDetails
 *
 *  进入老年代的对象：
 *      1.大对象，为了避免出发分配担保机制导致效率降低，大对象直接在老年代分配
 *      2.长期存活对象，每次经过MinorGC之后，suivior区对象年龄 +1，超过阈值（默认15）就会存放到老年代
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/9 14:13
 */
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1,allocation2;

        allocation1 = new byte[58800 * 1024];
        //allocation1 已经占满eden区，allocation2 分配内存时会发生MinorGC,但是survior区域也不够存放，分配担保机制
        //会让allocation1直接进入老年代
        allocation2 = new byte[9000 * 1024];
    }
}
