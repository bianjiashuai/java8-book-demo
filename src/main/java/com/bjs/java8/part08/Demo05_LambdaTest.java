package com.bjs.java8.part08;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description lambda测试
 * @Author BianJiashuai
 */
public class Demo05_LambdaTest {

    @Test
    public void testPrintStream() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        // 通过 peek 操作我们能清楚地了解流水线操作中每一步的输出结果
        List<Integer> result = numbers.stream()
                        .peek(x -> System.out.println("流开始前: " + x))
                        .map(x -> x + 17)
                        .peek(x -> System.out.println("map之后: " + x))
                        .filter(x -> x % 2 == 0)
                        .peek(x -> System.out.println("filter之后: " + x))
                        .limit(3)
                        .peek(x -> System.out.println("limit之后: " + x))
                        .collect(toList());
        System.out.println(result);
    }

    @Test
    public void testFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = filter(numbers, i -> i < 3);
        assertEquals(Arrays.asList(2, 4), even);
        assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }


    public<T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> res = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                res.add(t);
            }
        }
        return res;
    }
}
