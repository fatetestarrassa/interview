package com.icyfate.interview.test.core.PackClass;

/**
 *  1.自动装箱：Integer i = 0 ;相当于Integer i = Integer.valueOf(0);
 *    自动拆箱：int i2 = i;相当于 int i2 = i.intValue();
 *
 *  2.Byte Short Integer Long 已经缓存了 -128 到 127的值(用的IntegerCace、LongCache等等里面put一个个new出来的对象)
 *    Character里缓存了 0到127里的值
 *    Boolean缓存的true 和 false
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/10 17:33
 */
public class PackClass {
    public static void main(String[] args) {
        Integer i1 = 33;
        Integer i2 = 33;
        System.out.println(i1==i2);

        Integer i3 = new Integer(33);
        System.out.println(i2==i3);

    }
}
