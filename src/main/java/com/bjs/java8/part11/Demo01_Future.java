package com.bjs.java8.part11;

import java.util.concurrent.*;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_Future {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() {
                return doSomeCompute(); // 以异步方式在新的线程执行一个耗时的操作
            }
        });
        doSomeElse();   // 异步操作的同时，可以去做其他事情
        try {
            Double res = future.get(2, TimeUnit.SECONDS);// 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

    public static void doSomeElse() {
        System.out.println("假装在做别的事情");
    }

    public static Double doSomeCompute() {
        // 假装（模拟）这是个非常耗时的操作
        try {
            Thread.sleep(1000);
            System.out.println("Hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double res = 1000;
        return res;
    }
}
