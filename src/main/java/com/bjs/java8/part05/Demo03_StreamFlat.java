package com.bjs.java8.part05;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 流的扁平化
 * @Author BianJiashuai
 */
public class Demo03_StreamFlat {

    public static void main(String[] args) {
        //对于一张单词表，如何返回一张列表，列出里面各不相同的字符呢？
        // 例如，给定单词列表["Hello","World"] ，你想要返回列表 ["H","e","l", "o","W","r","d"]
//        demo1();
//        demo2_ArraysStream();
        demo_flatmap();

    }

    // 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]
    @Test
    public void test() {
        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5).stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println(collect);
    }

    // 给定两个数字列表，如何返回所有的数对呢？
    // 例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
    @Test
    public void test2() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> collect = num1.stream().flatMap(n1 -> num2.stream().map(n2 -> new int[]{n1, n2})).collect(Collectors.toList());

        System.out.println(collect.stream().map(Arrays::toString).collect(Collectors.toList()));

        // 扩展上面例子，只返回总和能被3整除的数对
        List<String> strings = num1.stream()
                .flatMap(n1 -> num2.stream()
                        .filter(n2 -> (n1 + n2) % 3 == 0)
                        .map(n2 -> new int[]{n1, n2}))
                .map(Arrays::toString)
                .collect(Collectors.toList());
        System.out.println(strings);
    }

    //flatmap 方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
    public static void demo_flatmap() {
        List<String> words = Arrays.asList("Hello", "World");
        List<Object> collect = words.stream().map(w -> w.split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * .map(Arrays::stream) 让每个数组变成一个单独的流
     * 此方法会返回一个流的列表，跟想要的仍然不匹配
     */
    public static void demo2_ArraysStream() {
        String[] worlds = {"Hello", "World"};
        List<Stream<String>> collect = Arrays.stream(worlds)
                        .map(w -> w.split(""))
                        .map(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
    }

    // 此方法会返回Stream<String[]> 类型，跟想要的不匹配
    public static void demo1() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> collect = words.stream().map(w -> w.split("")).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}
