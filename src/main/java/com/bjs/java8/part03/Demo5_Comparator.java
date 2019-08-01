package com.bjs.java8.part03;

import static java.util.Comparator.comparing;

import com.bjs.java8.part01.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo5_Comparator {


    public static void main(String[] args) {
//        comparing_demo();
//        fuhe_predicate_demo();

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g); // g(f(x))
        System.out.println(h1.apply(1)); // 4
        Function<Integer, Integer> h2 = f.compose(g); // f(g(x))
        System.out.println(h2.apply(1)); // 3

        Function<String, Integer> mm = String::length;

    }

    public static void fuhe_predicate_demo() {
        // 红苹果
        Predicate<Apple> redApple = (a) -> "red".equals(a.getColor());
        // 取 `非` 操作， 非红苹果
        Predicate<Apple> notRedApple = redApple.negate();
        // 取交集， 重量大于150g的红苹果
        Predicate<Apple> redAndHeavy150 = redApple.and((a) -> a.getWeight() > 150);
        // 取并集， 红苹果或者重量大于150g的苹果
        Predicate<Apple> redOrHeavy150 = redApple.or(a -> a.getWeight() > 150);
        // 要么是重（150克以上）的红苹果，要么是绿苹果
        Predicate<Apple> readAndHeavyOrGreen = redApple.or(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));
    }

    /**
     * 比较器链
     */
    public static void comparing_demo() {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(120, "苹果1", "green"));
        inventory.add(new Apple(150, "苹果2", "red"));
        inventory.add(new Apple(150, "苹果3", "green"));
        inventory.add(new Apple(150, "苹果4", "blue"));
        inventory.add(new Apple(210, "苹果5", "green"));
        inventory.add(new Apple(190, "苹果6", "red"));

        inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));
        System.out.println(inventory);
    }
}
