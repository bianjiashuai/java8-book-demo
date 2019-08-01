package com.bjs.java8.part07;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Description
 * @Author BianJiashuai
 */

//继承RecursiveTask来创建可以用于分支/合并框架的任务
public class Demo02_ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] number;    // 需要求和的数组
    private final int start;        // 子任务数组的开始位置
    private final int end;          // 子任务数组的结束位置

    public static final long THRESHOLD = 10_000; // 不在分解的数组长度限制

    public Demo02_ForkJoinSumCalculator(long[] number) {
        this(number, 0, number.length);
    }

    private Demo02_ForkJoinSumCalculator(long[] number, int start, int end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // 数组长度小于阈值，直接计算返回
            return computeSequentially();
        }

        // 左半边的任务
        Demo02_ForkJoinSumCalculator leftTask =
                new Demo02_ForkJoinSumCalculator(number, start, start + length / 2);
        // 任务异步执行
        leftTask.fork();
        // 右半边的任务
        Demo02_ForkJoinSumCalculator rightTask = new Demo02_ForkJoinSumCalculator(number, start + length / 2, end);
        // 同步执行
        Long rightResult = rightTask.compute();
        // 读取左半边任务的结果，如果尚未完成则等待直到完成
        Long leftResult = leftTask.join();
        // 计算结果返回
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += number[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(forkJoinSum(1_000_000));
        System.out.println(Demo01_ParallelStream.measureSumPref(Demo02_ForkJoinSumCalculator::forkJoinSum, 1_000_000));
    }

    public static long forkJoinSum(long n) {
        long[] number = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new Demo02_ForkJoinSumCalculator(number);
        return task.invoke();
    }
}
