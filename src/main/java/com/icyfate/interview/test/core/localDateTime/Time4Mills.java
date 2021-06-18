package com.icyfate.interview.test.core.localDateTime;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/8 15:06
 */
public class Time4Mills {

    public static void main(String[] args) {
//        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
//                // 解析date+time
//                .appendPattern("yyyy-MM-dd HH:mm:ss")
//                // 解析毫秒数
//                .appendValue(ChronoField.MILLI_OF_SECOND, 3)
//                .toFormatter();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                LocalDateTime maxCreatedTimeDate = LocalDateTime.parse("2021-06-08 14:48:25.111",dtf);
        System.out.println(maxCreatedTimeDate);
    }
}
