package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.menu;
import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Description 开发自己的ToListCollector
 * @Author BianJiashuai
 */
public class Demo06_ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    public static void main(String[] args) {
        menu.stream().collect(new Demo06_ToListCollector<>()).forEach(System.out::println); // 自定义收集器收集方式
        System.out.println("--------------------------------------");
        menu.stream().collect(Collectors.toList()).forEach(System.out::println); // JDK提供的收集方式
        System.out.println("--------------------------------------");
        // 进行自定义手机而不去实现Collector
        menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll
        ).forEach(System.out::println);
    }

    // 创建集合操作的起始点
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    // 累积遍历过的项目，原位修改累加器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    //修改第一个累加器，将其与第二个累加器合并，然后返回修改后的第一个累加器
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    // 恒等函数
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    // 为收集器添加 IDENTITY_FINISH 和 CONCURRENT 标志
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
