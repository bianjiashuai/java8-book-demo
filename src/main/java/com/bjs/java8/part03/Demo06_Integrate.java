package com.bjs.java8.part03;

import java.util.function.DoubleFunction;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo06_Integrate {

    public static void main(String[] args) {
        System.out.println(integrate(new MyDoubleFunction(), 3, 7));
        System.out.println(integrate(x -> x + 10, 3, 7));
        System.out.println(integrate(C::f, 3, 7));
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public static class MyDoubleFunction implements DoubleFunction<Double> {
        @Override
        public Double apply(double value) {
            return value + 10;
        }
    }
}

class C {

    private String name;

    public static double f(double x) {
        return x + 10;
    }

    public double a() {
        return name.length();
    }
}
