package com.bjs.java8.part11;

import static com.bjs.java8.part11.Util.productList;

import com.bjs.java8.part11.bean.Product;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description 并行流和异步
 * 【*】内部采用的是同样的通用线程池，默认都使用固定数目的线程，具体线程数取决于
 * Runtime.getRuntime().availableProcessors() 的返回值。
 * 然而， CompletableFuture 具有一定的优势，因为它允许你对执行器（Executor）进行配置，尤其是线程池的大小，
 * 让它以更适合应用需求的方式进行配置，满足程序的要求，而这是并行流API无法提供的.
 * @Author BianJiashuai
 */
public class Demo03_CompletableFuture {

    /**
     * N threads = N CPU * U CPU * (1 + W/C)
     * 其中：
     * ❑N CPU 是处理器的核的数目，可以通过 Runtime.getRuntime().availableProcessors() 得到
     * ❑U CPU 是期望的CPU利用率（该值应该介于0和1之间）
     * ❑W/C是等待时间与计算时间的比率
     *
     */

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(productList.size(), 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        long start = System.nanoTime();
//        System.out.println(findPrices("myPhone27S"));
//        System.out.println(findPricesParallel("myPhone27S"));
//        System.out.println(findPricesSupplyAsync("myPhone27S"));
        System.out.println(findPricesSupplyAsync("myPhone27S", executor));
        System.out.println("耗时: " + (System.nanoTime() - start) / 1_000_000 + "毫秒");
    }

    /**
     * 使用Stream的特性
     *
     * @param name 产品名称
     * @return 返回一个字符串列表，这个字符串列表中包括产品的名称、该商品列表中指定产品的价格
     */
    public static List<String> findPrices(String name) {
        return productList.stream()
                .map(product -> String.format("%s price is%.2f", product.getName(), product.getPrice(name)))
                .collect(Collectors.toList());
    }

    private static List<String> findPricesParallel(String name) {
        return productList.parallelStream()
                .map(product -> String.format("%s price id %.2f", product.getName(), product.getPrice(name)))
                .collect(Collectors.toList());
    }

    private static List<String> findPricesSupplyAsync(String name) {
        List<CompletableFuture<String>> futures = productList.stream()
                .map(product -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price id %.2f", product.getName(), product.getPrice(name))))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<String> findPricesSupplyAsync(String name, Executor executor) {
        List<CompletableFuture<String>> futures = productList.stream()
                .map(product -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price id %.2f", product.getName(), product.getPrice(name)), executor))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

}
