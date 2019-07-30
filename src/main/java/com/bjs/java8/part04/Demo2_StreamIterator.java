package com.bjs.java8.part04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 流只能对一次，集合可以读多次
 * @Author BianJiashuai
 */
public class Demo2_StreamIterator {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java8", "In", "Action");
//        collect_read(list);
//        stream_read(list);
        List<String> names = Dish.menu.stream().filter(d -> {
            System.out.println("filter: " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("map: " + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(names);

        System.out.println("---------------------------");
        Dish.menu.stream().filter(d -> d.getCalories() > 410).map(Dish::getName).forEach(System.out::println);

    }

    public static void stream_read(List<String> list) {
        /**
         * 流重复读取,会报错：java.lang.IllegalStateException: stream has already been operated upon or closed
         */
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    /**
     * 集合重复读取，正常
     * @param list
     */
    public static void collect_read(List<String> list) {
        list.forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
