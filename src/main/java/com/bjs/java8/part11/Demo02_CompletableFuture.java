package com.bjs.java8.part11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description 使用 CompletableFuture 构建异步应用
 * @Author BianJiashuai
 */
public class Demo02_CompletableFuture {

    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> future = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + "毫秒");

        // 执行更多任务，比如查询其他商店
        doSomeElse();

        // 在计算商品价格的同时
        try {
            Double price = future.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + "毫秒");
    }

    public static void doSomeElse() {
        System.out.println("假装在做别的事情");
    }
}

class Shop {

    // 同步阻塞
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        // 创建 CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> future = new CompletableFuture<>();
        // 在另一个线程中以异步方式执行计算
        new Thread(
                () -> {
                    // 需长时间计算的任务结束并得出结果时，设置Future 的返回值
                    /**
                     * 如果没有意外，我们目前开发的代码工作得很正常。但是，如果价格计算过程中产生了错误
                     * 会怎样呢？非常不幸，这种情况下你会得到一个相当糟糕的结果：用于提示错误的异常会被限制
                     * 在试图计算商品价格的当前线程的范围内，最终会杀死该线程，而这会导致等待 get 方法返回结
                     * 果的客户端永久地被阻塞
                     * 【解决1】：客户端使用get的重载方法，使用超时参数避免永久阻塞
                     * 【解决2】：使用completeExceptionally 方法将导致 CompletableFuture 内发生问题的异常抛出
                     */
//                    double price = calculatePrice(product);
//                    future.complete(price);

                    // 【******下面为解决示例******】
                    try {
                        double price = calculatePrice(product);
                        future.complete(price);
                    } catch (Exception ex) {
                        future.completeExceptionally(ex);
                    }
                }
        ).start();
        // 无需等待还没结束的计算，直接返回 Future 对象
        return future;
    }

    // 使用supplyAsync可以一句话重写getPriceAsync方法
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟一秒的延迟
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

