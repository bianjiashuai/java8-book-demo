package com.bjs.java8.part05;

import static com.bjs.java8.part04.Dish.menu;

import com.bjs.java8.part04.Dish;

import java.util.Arrays;

/**
 * @Description 筛选切片
 * @Author BianJiashuai
 */
public class Demo01_ScreenAndSlice {

    public static void main(String[] args) {
//        demo_distinct();
//        demo_limit();
//        demo_skip();

        // 需求:利用流来筛选前两个荤菜
        menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).forEach(System.out::println);


    }

    // 元素跳过，刚还与截断互补
    public static void demo_skip() {
        menu.stream().filter(d -> d.getCalories() > 300).forEach(System.out::println);
        System.out.println("-----------------");
        menu.stream().filter(d -> d.getCalories() > 300).skip(2).forEach(System.out::println);
    }

    // 截断，限制截取数量（不会对元素进行排序）
    public static void demo_limit() {
        menu.stream().filter(d -> d.getCalories() > 300).forEach(System.out::println);
        System.out.println("-----------------");
        menu.stream().filter(d -> d.getCalories() > 300).limit(3).forEach(System.out::println);
    }

    // 去重（根据元素的hashcode和equals方法判断）
    public static void demo_distinct() {
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println("-----------------");
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(i -> i % 2 != 0).distinct().forEach(System.out::println);
    }
}
