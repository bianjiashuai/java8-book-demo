package com.bjs.java8.part11.bean;

import com.sun.org.apache.regexp.internal.RE;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @Description
 * @Author BianJiashuai
 */
public class DisCount {

    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }

    }

    public static String applyDiscount(Quote quote) {
        //将折扣代码应用于商品最初的原始价格
        return quote.getProductName() + " price is " + apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        Product.delay();    // 模拟 Discount 服务响应的延迟
        return format(price * (100 - code.percentage) / 100);
    }

    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }
}
