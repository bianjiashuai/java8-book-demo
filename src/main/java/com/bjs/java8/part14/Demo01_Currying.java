package com.bjs.java8.part14;

import java.util.function.DoubleUnaryOperator;

/**
 * @Description 柯里化
 * @Author BianJiashuai
 */
public class Demo01_Currying {


    // x 是你希望转换的数量， f 是转换因子， b 是基线值
    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return x -> x * f + b;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }

    public static void main(String[] args) {
//        将摄氏度转换到华氏度的公式是 CtoF(x) = x*9/5 + 32
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        System.out.println(convertCtoF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0/9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }
}
