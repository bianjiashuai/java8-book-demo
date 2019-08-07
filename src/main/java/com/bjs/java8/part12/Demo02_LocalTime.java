package com.bjs.java8.part12;

import java.time.LocalTime;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo02_LocalTime {

    public static void main(String[] args) {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
    }
}
