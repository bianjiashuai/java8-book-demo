package com.bjs.java8.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author BianJiashuai
 */

public class Demo2_FilterApple {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(120, "苹果1", "green"));
        inventory.add(new Apple(150, "苹果2", "red"));
        inventory.add(new Apple(135, "苹果3", "green"));
        inventory.add(new Apple(188, "苹果4", "blue"));
        inventory.add(new Apple(210, "苹果5", "green"));
        inventory.add(new Apple(190, "苹果6", "red"));

        System.out.println(filterGreenApples(inventory));
        System.out.println(filterHeavy150Apples(inventory));

        System.out.println("--------------------------------------------------------------");
        System.out.println(filterApples(inventory, Demo2_FilterApple::isGreenApple));
        System.out.println(filterApples(inventory, Demo2_FilterApple::isHeavy150Apple));

        System.out.println("--------------------------------------------------------------");
        System.out.println(filterApples(inventory, apple -> "green".equals(apple.getColor())));
        System.out.println(filterApples(inventory, apple -> apple.getWeight() > 150));

    }

    /**
     * 【传统做法】绿苹果
     *
     * @param inventory 库存集合
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 【传统做法】重量超过150库存集合
     *
     * @param inventory
     * @return
     */
    public static List<Apple> filterHeavy150Apples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavy150Apple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {     //谓语，平常只要从java.util.function导入就可以了
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) { //方法作为Predicate参数p传递进去
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
        if (p.test(apple)) {
        result.add(apple);
        }
        }
        return result;
        }

        }
