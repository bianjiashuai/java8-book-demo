package com.bjs.java8.part12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_LocalDate {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2019, 8, 15); // 创建一个日期
        int year = date.getYear();  // 获取年
        Month month = date.getMonth();  // 获取月份
        int dayOfMonth = date.getDayOfMonth();  // 获取月的一天
        DayOfWeek dayOfWeek = date.getDayOfWeek();  // 获取星期
        int lengthOfMonth = date.lengthOfMonth();   // 获取当月总天数
        boolean leapYear = date.isLeapYear();   // 是否是闰年
        System.out.println(year);
        System.out.println(month);
        System.out.println(dayOfMonth);
        System.out.println(dayOfWeek);
        System.out.println(lengthOfMonth);
        System.out.println(leapYear);
        System.out.println(LocalDate.now());    // LocalDate.now() 系统时钟中获取当前的日期
        System.out.println("----------------------");
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));


        // 以下两种方式的方法调用不改变原有对象本身，返回一个新的对象
        System.out.println("----------------------");
        // 以比较直观的方式操纵 LocalDate 的属性
        System.out.println(date.withYear(2015));
        System.out.println(date.withDayOfMonth(25));
        System.out.println(date.with(ChronoField.MONTH_OF_YEAR, 9));

        System.out.println("----------------------");
        // 以相对方式修改LocalDate
        System.out.println(date.plusWeeks(1));
        System.out.println(date.minusYears(1));
        System.out.println(date.plus(6, ChronoUnit.MONTHS));

    }
}
