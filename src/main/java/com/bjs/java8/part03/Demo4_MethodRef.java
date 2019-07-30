package com.bjs.java8.part03;

import com.bjs.java8.park01.Apple;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo4_MethodRef {

    static Map<String, BiFunction<Integer, String, Apple>> maps = new HashMap<>();
    static {
        maps.put("color", Apple::new);
        maps.put("green", Apple::new);
        maps.put("red", Apple::new);
    }

    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
        System.out.println(giveMeApple(1, "苹果", "red"));
    }

    public static Apple giveMeApple(Integer w, String n, String c) {
        return maps.get(c).apply(w, n);
    }

    public static void demo5() {
        Map<Integer, String> maps = new HashMap<>();
        maps.put(1, "第1只");
        maps.put(2, "第2只");
        maps.put(3, "第3只");
        List<Apple> list = map(maps, Apple::new);
        System.out.println(list);
    }

    public static List<Apple> map(Map<Integer, String> maps, BiFunction<Integer, String, Apple> bf) {
        List<Apple> result = new ArrayList<>();
        for (Map.Entry<Integer, String> en : maps.entrySet()) {
            result.add(bf.apply(en.getKey(), en.getValue()));
        }
        return result;
    }

    public static void demo4() {
        BiFunction<Integer, String, Apple> b1 = new BiFunction<Integer, String, Apple>() {
            @Override
            public Apple apply(Integer integer, String s) {
                return new Apple(integer, s);
            }
        };
        Apple apple = b1.apply(1, "第一种");
        System.out.println(apple);

        BiFunction<Integer, String, Apple> b2 = (w, n) -> new Apple(w, n);
        Apple apple2 = b2.apply(2, "第二种");
        System.out.println(apple2);

        BiFunction<Integer, String, Apple> b3 = Apple::new;
        Apple apple3 = b3.apply(3, "第三种");
        System.out.println(apple3);
    }

    /**
     * Supplier接口签名演示
     */
    public static void demo3() {
        Supplier<Apple> c1 = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return new Apple();
            }
        };

        Supplier<Apple> c2 = () -> new Apple();

        Supplier<Apple> c3 = Apple::new;
    }

    public static void demo2() {
        List<String> str = Arrays.asList("a", "b", "A", "B");
        System.out.println(str);
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);
    }

    public static void demo1() {
        List<String> str = Arrays.asList("a", "b", "A", "B");
        System.out.println(str);
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);
    }
}
