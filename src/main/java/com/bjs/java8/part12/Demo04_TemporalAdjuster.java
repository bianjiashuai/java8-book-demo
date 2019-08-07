package com.bjs.java8.part12;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo04_TemporalAdjuster {

//    dayOfWeekInMonth  创建一个新的日期，它的值为同一个月中每一周的第几天
//    firstDayOfMonth  创建一个新的日期，它的值为当月的第一天
//    firstDayOfNextMonth  创建一个新的日期，它的值为下月的第一天
//    firstDayOfNextYear  创建一个新的日期，它的值为明年的第一天
//    firstDayOfYear  创建一个新的日期，它的值为当年的第一天
//    firstInMonth  创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
//    lastDayOfMonth  创建一个新的日期，它的值为当月的最后一天
//    lastDayOfNextMonth  创建一个新的日期，它的值为下月的最后一天
//    lastDayOfNextYear  创建一个新的日期，它的值为明年的最后一天
//    lastDayOfYear  创建一个新的日期，它的值为今年的最后一天
//    lastInMonth  创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
//    next/previous 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
//    nextOrSame/previousOrSame 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2019, 8, 5);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);

        System.out.println("----------------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime time = LocalDateTime.now();
        String formatDate = time.format(formatter);
        LocalDateTime parseDate = LocalDateTime.parse(formatDate, formatter);
        System.out.println(formatDate);
        System.out.println(parseDate);

        System.out.println("----------------------------");
        DateTimeFormatter cusFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINA);
        System.out.println(time.format(cusFormatter));
    }

}