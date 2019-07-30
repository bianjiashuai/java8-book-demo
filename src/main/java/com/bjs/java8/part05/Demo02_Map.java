package com.bjs.java8.part05;

import static com.bjs.java8.part04.Dish.menu;

import com.bjs.java8.part04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description 映射
 * @Author BianJiashuai
 */
public class Demo02_Map {

    public static void main(String[] args) {
//        demo_map_trans();

//        demo_map_stringLen();

        // 需求：找出每道菜的名称有多长
        menu.stream().map(Dish::getName).map(String::length).forEach(System.out::println);
    }

    public static void demo_map_stringLen() {
        List<String> words = Arrays.asList("Java8", "In", "Action");
        words.stream().map(String::length).forEach(System.out::println);
    }

    public static void demo_map_trans() {
        // 匿名内部类 方式
        menu.stream().map(new Function<Dish, Object>() {
            @Override
            public Object apply(Dish dish) {
                return dish.getName();
            }
        }).collect(Collectors.toList());

        // lambda 方式
        menu.stream().map(d -> d.getName()).collect(Collectors.toList());

        // 方法引用方式
        menu.stream().map(Dish::getName).collect(Collectors.toList());
    }
}
