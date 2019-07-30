package com.bjs.java8.part03;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo2_ConsumerForEach {

    public static<T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
    }

}
