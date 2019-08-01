package com.bjs.java8.part05;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description 构建流
 * @Author BianJiashuai
 */
public class Demo07_BuildStream {

    public static void main(String[] args) {
//        demo_streamOf();
//        demo_arrayStream();
//        demo_fileStream();

        /**
         * Stream API提供了两个静态方法来从函数生成流： Stream.iterate 和 Stream.generate
         * 这两个操作可以创建所谓的无限流，无限流不可以做排序或者归约
         */
//        demo_StreamIterate();
        demo_StreamGenerate();
    }

    public static void demo_StreamGenerate() {
        Stream.generate(Math::random)
                .limit(5).forEach(System.out::println);
    }

    public static void demo_StreamIterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10).forEach(System.out::println);
    }

    // 斐波那契元组， 如(1, 1), (1, 2), (2, 3), (3, 5), (5, 8), ...
    @Test
    public void fibonacciTuple() {
        Stream.iterate(new int[]{1, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(20).forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
    }

    @Test
    public void fibonacciIterator() {
        Stream.iterate(new int[]{1, 1}, ints -> new int[]{ints[1], ints[0] + ints[1]})
                .limit(10).forEach(t -> System.out.println(t[0]));
    }

    // 斐波那契数列， 如1, 2, 3, 5, 8, 13, ...
    @Test
    public void fibonacci() {
        /**
         * 【说明】相比之下，使用 iterate 的方法则是纯粹不变的：它没有修改现有状态，但在每次迭代时会创建新的元组。
         *        你应该始终采用不变的方法，以便并行处理流，并保持结果正确【这里只做演示】
         */
        IntSupplier fib = new IntSupplier() {
            private int previous = 1;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int next = this.previous + this.current;
                this.previous = this.current;
                this.current = next;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    // 由文件构建流 Files类中方法
    public static void demo_fileStream() {
        long uniqueLines = 0;
        try (Stream<String> lines = Files.lines(Paths.get("bjs-test/data.txt"), Charset.defaultCharset())) {
            uniqueLines = lines.flatMap(line -> Arrays.stream(line.split("")))
                    .distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueLines);
    }

    // 由数组构建流 Arrays.stream
    public static void demo_arrayStream() {
        int[] numbers = {2, 3, 5, 7, 13};
        System.out.println(Arrays.stream(numbers).sum());
    }

    // 由值创建流
    public static void demo_streamOf() {
        Stream<String> stream = Stream.of("Java8", "Lambda", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        // 使用empty得到一个空流
        Stream<Object> emptyStream = Stream.empty();
    }
}
