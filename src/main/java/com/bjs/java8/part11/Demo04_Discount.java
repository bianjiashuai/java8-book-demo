package com.bjs.java8.part11;

import static com.bjs.java8.part11.Util.productList;

import com.bjs.java8.part11.bean.DisCount;
import com.bjs.java8.part11.bean.ExchangeService;
import com.bjs.java8.part11.bean.Product;
import com.bjs.java8.part11.bean.Quote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo04_Discount {

    static Executor executor = Executors.newFixedThreadPool(Math.min(productList.size(), 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        long start = System.nanoTime();
//        System.out.println(findPrices("myPhone27S"));
        System.out.println(findPrices2("myPhone27S"));
        System.out.println("耗时: " + (System.nanoTime() - start) / 1_000_000 + "毫秒");
    }


    public List<String> findPricesInUSD(String name) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Product product : productList) {
            CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> product.getPrice(name))
                    .thenCombine(CompletableFuture.supplyAsync(
                            () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)
                    ), (price, rate) -> price * rate);
            priceFutures.add(future);
        }

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .map(price -> " price is " + price)
                .collect(Collectors.toList());
    }


    public List<String> findPricesInUSDJava7(String name) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Product product : productList) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                public Double call() {
                    return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                public Double call() {
                    try {
                        double priceInEUR = product.getPrice(name);
                        return priceInEUR * futureRate.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

    public static List<String> findPrices(String name) {
        return productList.stream()
                .map(product -> product.getPriceStr(name))
                .map(Quote::parse)
                .map(DisCount::applyDiscount)
                .collect(Collectors.toList());
    }


    public static List<String> findPrices2(String name) {
        List<CompletableFuture<String>> futures = productList.stream()
                .map(product -> CompletableFuture.supplyAsync(() -> product.getPriceStr(name), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> // thenCompose方法对两个异步操作进行流水线
                        CompletableFuture.supplyAsync(
                                () -> DisCount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static Stream<CompletableFuture<String>> finPricesStream(String name) {
        return productList.stream()
                .map(product -> CompletableFuture.supplyAsync(() -> product.getPriceStr(name), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future  -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> DisCount.applyDiscount(quote), executor)));
    }
}
