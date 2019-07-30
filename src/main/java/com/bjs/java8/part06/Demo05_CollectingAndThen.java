package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.menu;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

/**
 * @Description collectingAndThen: 转换函数返回的类型
 * @Author BianJiashuai
 */
public class Demo05_CollectingAndThen {
    public static void main(String[] args) {
        Integer collect = menu.stream().collect(collectingAndThen(toList(), List::size));
        System.out.println(collect);

        menu.stream().collect(toList()).forEach(System.out::println);
    }
}
