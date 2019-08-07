package com.bjs.java8.part11;

import com.bjs.java8.part11.bean.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Util {

    public static List<Product> productList = Arrays.asList(
            new Product("BestPrice"), new Product("LetsSaveBig"),
            new Product("MyFavoriteShop"), new Product("BuyItAll"),
            new Product("ShopEasy01"), new Product("ShopEasy02"),
            new Product("ShopEasy03"), new Product("ShopEasy04"),
            new Product("ShopEasy05"));

    private static final Random random = new Random();

    // 一个模拟生成0.5秒至2.5秒随机延迟的方法
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
