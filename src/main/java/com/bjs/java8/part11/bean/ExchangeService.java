package com.bjs.java8.part11.bean;

/**
 * @Description
 * @Author BianJiashuai
 */
public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }


    public static double getRate(Money src, Money dest) {
        Product.delay();    // 模拟汇率服务器响应延迟
        return dest.rate / src.rate;
    }
}
