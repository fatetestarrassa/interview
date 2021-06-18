package com.icyfate.interview.test.core;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * BigDecimal:
 *      1.比较小数是否相等（float和double由于精度问题不能比较）
 *      2.比较大小
 *      3.设置位数，和保留策略
 *      4.注意点：禁止new BigDecimal(double)，参数使用string类型，或者BigDecimal.valOf(double);
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/11 16:55
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        float f1 = 1.0f - 0.9f;
        float f2 = 0.9f - 0.8f;
        System.out.println(f1 == f2);

        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        System.out.println(Objects.equals(x,y));

        System.out.println(a.compareTo(b));

        BigDecimal m = new BigDecimal("1.255433");
        BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(n);

    }
}
