package com.bjs.java8.part12;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo03_LocalDateTime {

    public static void main(String[] args) {
        LocalDateTime dt1 = LocalDateTime.of(2019, Month.AUGUST, 5, 15, 31, 58);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt2 = LocalDateTime.of(date, time);

        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);
        System.out.println(dt5);

        System.out.println("-----------------------");
        System.out.println(dt2.toLocalDate());
        System.out.println(dt2.toLocalTime());
        System.out.println(LocalDateTime.now());

        System.out.println("-----------------------");
        // 2秒之后再加上100万纳秒（1秒）
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));

        System.out.println("------------------------");

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinute = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        System.out.println(threeMinutes);
        System.out.println(threeMinute);
        System.out.println(tenDays);
        System.out.println(threeWeeks);
        System.out.println(twoYearsSixMonthsOneDay);

    }
}
