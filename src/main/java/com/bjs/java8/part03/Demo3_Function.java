package com.bjs.java8.part03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo3_Function {

    public static<T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> map = map(Arrays.asList("lambdas", "in", "action"), (s) -> s.length());
        System.out.println(map);
    }
}
