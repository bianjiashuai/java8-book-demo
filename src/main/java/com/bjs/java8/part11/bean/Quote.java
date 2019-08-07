package com.bjs.java8.part11.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 商品、价格、折扣 封装对象
 * @Author BianJiashuai
 */
@Getter
@AllArgsConstructor
public class Quote {

    private final String productName;
    private final Double price;
    private final DisCount.Code discountCode;

    public static Quote parse(String s) {
        String[] split = s.split(":");
        double price = Double.parseDouble(split[1]);
        DisCount.Code discountCode = DisCount.Code.valueOf(split[2]);
        return new Quote(split[0], price, discountCode);
    }
}
