package com.bjs.java8.part03.cus;

import com.bjs.java8.part01.Apple;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Cus_Lambda_Test {
    public static void main(String[] args) {
        TriFunction<Integer, String, String, Apple> tf = Apple::new;
        Apple apple = tf.apply(1, "苹果", "red");
        System.out.println(apple);
    }

}
