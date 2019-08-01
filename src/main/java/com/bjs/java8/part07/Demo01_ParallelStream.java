package com.bjs.java8.part07;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Description 测量流性能
 *      这相当令人失望，求和方法的并行版本比顺序版本要慢很多。这里实际上有两个问题：
 *        iterate 生成的是装箱的对象，必须拆箱成数字才能求和；
 *        我们很难把 iterate 分成多个独立块来并行执行。
 * @Author BianJiashuai
 */
public class Demo01_ParallelStream {


    public static void main(String[] args) {
        System.out.println("Sequential sum done in: "
                + measureSumPref(Demo01_ParallelStream::sequentialSum, 1_000_000) + " 毫秒");
        System.out.println("-----------------------------------------------");
        System.out.println("parallel sum done in: "
                + measureSumPref(Demo01_ParallelStream::parallelSum, 1_000_000) + " 毫秒");
        System.out.println("-----------------------------------------------");
        System.out.println("parallelRange sum done in: "
                + measureSumPref(Demo01_ParallelStream::parallelRangeSum, 1_000_000) + " 毫秒");
        System.out.println("-----------------------------------------------");
        System.out.println("iterative sum done in: "
                + measureSumPref(Demo01_ParallelStream::iterativeSum, 1_000_000) + " 毫秒");
        System.out.println("-----------------------------------------------");
        System.out.println("range sum done in: "
                + measureSumPref(Demo01_ParallelStream::rangeSum, 1_000_000) + " 毫秒");
    }
    public static long measureSumPref(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    // 并行求和
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    // 并行求和
    public static long parallelRangeSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    // 串行求和
    public static long iterativeSum(long n) {
        long res = 0;
        for (long i = 1L; i <= n; i++) {
            res += i;
        }
        return res;
    }

    // 串行归约求和
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long rangeSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }
}
