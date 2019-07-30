package com.bjs.java8.part05;

import static com.bjs.java8.part04.Dish.menu;

import com.bjs.java8.part04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Description 查找和匹配(allMatch、anyMatch、noneMatch、findFirst、findAny)
 * @Author BianJiashuai
 */
public class Demo04_SelectAndMatch {

    public static void main(String[] args) {
//        demo_anyMatch();
//        demo_allMatch();
//        demo_noneMatch();
//        demo_findAny();
//        demo_findFirst();

        /**
         * 何时使用 findFirst 和 findAny ???
         * 找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流时限制较少
         *
         * 如下面的例子...
         */
        demo_parallel_findAny();

    }

    // 有些流有一个出现顺序来指定流中项目出现的逻辑顺序（比如由 List 或排序好的数据列生成的流）。
    // 对于这种流，你可能想要找到第一个元素。为此有一个findFirst方法，它的工作方式类似于findAny
    public static void demo_findFirst() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> optional = num.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        optional.ifPresent(System.out::println);
    }

    // findAny 方法将返回当前流中的任意元素, 它可以与其他流操作结合使用, 只要找到就返回，不会全部查找（利用短路逻辑）
    public static void demo_findAny() {
        Optional<Dish> optional = menu.stream().filter(Dish::isVegetarian).findAny();
        optional.ifPresent(System.out::println);
    }

    public static void demo_parallel_findAny() {
        Optional<Dish> optional = menu.parallelStream().filter(Dish::isVegetarian).findAny();
        optional.ifPresent(System.out::println);
    }

    // noneMatch和allMatch相对。它可以确保流中没有任何元素与给定的谓词匹配
    public static void demo_noneMatch() {
        // 没有任何一道菜的热量高于1000卡路里
        System.out.println(menu.stream().noneMatch(d -> d.getCalories() > 1000));
        // 没有任何一道菜的热量低于500卡路里
        System.out.println(menu.stream().noneMatch(d -> d.getCalories() < 500));
    }

    // allMatch 方法的工作原理和 anyMatch 类似，但它会看看流中的元素是否都能匹配给定的谓词
    public static void demo_allMatch() {
        // 所有菜的热量都低于1000卡路里
        System.out.println(menu.stream().allMatch(d -> d.getCalories() < 1000));
        // 所有菜的热量都高于500卡路里
        System.out.println(menu.stream().allMatch(d -> d.getCalories() > 500));
    }

    // anyMatch 方法可以回答“流中是否有一个元素能匹配给定的谓词
    public static void demo_anyMatch() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!");
        }
    }
}
