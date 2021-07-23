package com.icyfate.interview.test.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/22 15:50
 */
public class DateApiDemo {
    public static void main(String[] args) {
        //Clock类
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        Instant instant = clock.instant();
        System.out.println(instant);
        Date date = Date.from(instant);
        System.out.println(date);

        //TimeZone  时区

        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        //LocalTime
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now1.isAfter(now2));
        System.out.println(now1.isBefore(now2));

        long hourBetween = ChronoUnit.HOURS.between(now1,now2);
        long minuteBetween = ChronoUnit.MINUTES.between(now1,now2);
        System.out.println(hourBetween);
        System.out.println(minuteBetween);

        //LocalDate

        String str2 = "2014$$$四月$$$13 20小时";
        DateTimeFormatter fomatter2 = DateTimeFormatter
                .ofPattern("yyy$$$MMM$$$dd HH小时");
        LocalDateTime dt2 = LocalDateTime.parse(str2, fomatter2);
        System.out.println(dt2);
    }
}
