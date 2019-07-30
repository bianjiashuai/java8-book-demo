package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.menu;
import static java.util.stream.Collectors.*;

import com.bjs.java8.part04.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;


/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo02_CollectData {

    public static void main(String[] args) {
//        demo01();

//        demo_joining();
    }

    /**
     * 字符串拼接：对流中每一个对象应用 toString 方法得到的所有字符串连接成一个字符串
     * joining()   直接拼接
     * joining("分割字符")  拼接中添加指定分割字符
     * joining("分割字符, 前缀, 后缀") 上面的基础上在最终的字符串分别添加指定的前缀和后缀
     */
    public static void demo_joining() {
        System.out.println(menu.stream().map(Dish::getName).collect(joining()));
        System.out.println(menu.stream().map(Dish::getName).collect(joining(", ")));
        System.out.println(menu.stream().map(Dish::getName).collect(joining(", ", "[", "]")));
    }

    // 计算和汇总
    public static void demo01() {
        // 热量总和
        Integer calories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(calories);

        // 热量平均值
        System.out.println(menu.stream().collect(averagingInt(Dish::getCalories)));

        // 热量最大值
        System.out.println(menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories))).get());

        // 热量最小值
        System.out.println(menu.stream().collect(minBy(Comparator.comparingInt(Dish::getCalories))).get());

        // 汇总(总数, 总和, 最小值, 平均值, 最大值)
        IntSummaryStatistics statistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(statistics);
    }
}
