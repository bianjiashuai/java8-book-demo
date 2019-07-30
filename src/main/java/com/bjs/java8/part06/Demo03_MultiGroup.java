package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.menu;
import static java.util.stream.Collectors.*;

import com.bjs.java8.part04.Dish;

import java.util.Comparator;

/**
 * @Description groupingBy(classifier, downstream)
 *                  classifier: 分组类目
 *                  downstream: 此类目的归约收集器
 * @Author BianJiashuai
 */
public class Demo03_MultiGroup {

    public static void main(String[] args) {
//        demo_group_level_1();

//        demo_group_level_2();

//        demo_group_count();

//        demo_group_mapping();

        menu.stream().collect(groupingBy(Dish::getType,
                maxBy(Comparator.comparingInt(Dish::getCalories)))
        ).forEach((k, v) -> System.out.println(k + ":" + v.get().getName()));

        // 再次记录：summarizingXXX具有各项信息的汇总功能(总数, 总和, 最小值, 平均值, 最大值)
        menu.stream().collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)))
                .forEach((k, v) -> System.out.println(k + ": " + v));

    }

    public static void demo_group_mapping() {
        // grouping-mapping
        menu.stream().collect(groupingBy(Dish::getType, mapping(Demo03_MultiGroup::myClassifier , toSet()))).forEach(
                (k, v) -> System.out.println(k + ": " + v)
        );
    }

    public static void demo_group_count() {
        menu.stream().collect(groupingBy(Dish::getType, counting())).forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        menu.stream().collect(groupingBy(Dish::getType, groupingBy(Demo03_MultiGroup::myClassifier, counting())))
                .forEach((k, v) -> v.forEach((ik, iv) -> System.out.println(k + "->" + ik + ":" + iv)));
    }

    public static<T extends Dish> String myClassifier(T t) {
        if (t.getCalories() <= 400) {
            return "DIET";
        } else if (t.getCalories() <= 700) {
            return "NORMAL";
        } else {
            return "FAT";
        }
    }

    public static void demo_group_level_2() {
        menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> myClassifier(dish)))).forEach((k, v) -> {
            System.out.println(k);
            v.forEach((ik, iv) -> {
                System.out.println("\t" + ik);
                iv.forEach(d -> {
                    System.out.println("\t\t" + d.getName());
                });
            });
        });
    }

    public static void demo_group_level_1() {
        menu.stream().collect(groupingBy(Dish::getType)).forEach((k, v) -> {
            System.out.println(k);
            v.stream().map(Dish::getName).forEach(n -> System.out.println("\t" + n));
        });
    }
}
