package com.bjs.java8.part02;

import com.bjs.java8.park01.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo1_FilterApple {

    /**
     * 【初试牛刀】, 筛选绿苹果
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
     * 以上针对多种颜色需求有有点力不从心了
     * 【再展身手】, 将颜色作为参数
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 以上无法针对其他属性做处理
     * 【再次尝试】对能想到的每个属性做筛选
     * List<Apple> greenApples = filterApples(inventory, "green", 0, true)
     * List<Apple> heavy150Apples = filterApples(inventory, "", 150, false)
     */
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 定义接口对选择标准进行建模
     */
    public interface ApplePredicate {
        boolean test(Apple apple);
    }
    // 选择重苹果
    public class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }
    // 选择绿苹果
    public class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }
    /**
     * 根据抽象条件筛选
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    // 找出所有重量超过150克的红苹果
    // List<Apple> redAndHeavy150Apples = filterApples(inventory, new AppleRedAndHeavy());
    public class AppleRedAndHeavy implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }

}
