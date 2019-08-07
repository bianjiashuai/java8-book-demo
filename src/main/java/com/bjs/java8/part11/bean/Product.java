package com.bjs.java8.part11.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @Description
 * @Author BianJiashuai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceStr(String product) {
        double price = calculatePrice(product);
        DisCount.Code code = DisCount.Code.values()[new Random().nextInt(DisCount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
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
