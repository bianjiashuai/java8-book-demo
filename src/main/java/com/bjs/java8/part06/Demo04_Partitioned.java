package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.menu;
import static java.util.stream.Collectors.*;

import com.bjs.java8.part04.Dish;

import java.util.stream.IntStream;

/**
 * @Description 分区：分区函数返回一个布尔值, 这意味着得到的分组Map的键类型是 Boolean,
 * 于是它最多可以分为两组—— true 是一组, false 是一组
 * @Author BianJiashuai
 */
public class Demo04_Partitioned {

    public static void main(String[] args) {
//        demo1_partitioned();
        int n = 10;
        IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(t -> isPrime(t))).forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static boolean isPrime(int candidate) {
        int candidateSqrt = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateSqrt).noneMatch(i -> candidate % i == 0);
    }

    public static void demo1_partitioned() {
        menu.stream().collect(partitioningBy(Dish::isVegetarian))
                .forEach((k, v) -> System.out.println(k + ": "
                        + v.stream().map(Dish::getName).collect(toList())));

        // 用同样的分区谓词，对菜单 List 创建的流作筛选，然后把结果收集到另外一个 List中也可以获得相同的结果
        String s = menu.stream().filter(Dish::isVegetarian).map(Dish::getName).collect(joining(", ", "[", "]"));
        System.out.println(s);
    }
}
