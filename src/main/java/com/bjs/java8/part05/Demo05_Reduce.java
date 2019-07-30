package com.bjs.java8.part05;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 归约（流中的值计算，比如汇总）
 *              举例："计算菜单中的总卡路里" 或 "菜单中卡路里最高的菜是哪一个"
 *
 *              定义：将流中所有元素反复结合起来，得到一个值，比如一个 Integer 。这样的查询可以被归类为归约操作（将流归约成一个值）。
 *                   用函数式编程语言的术语来说，这称为折叠（fold），
 *                   因为你可以将这个操作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果
 * @Author BianJiashuai
 */
public class Demo05_Reduce {

    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
//        demo_sum_foreach();
//        demo_sum_reduce();
//        demo_sum_reduce2();
//        demo_max_reduce(num);


    }

    public static void demo_max_reduce(List<Integer> num) {
        num.stream().reduce(Integer::max).ifPresent(System.out::println);
//        num.stream().reduce((x, y) -> x > y ? x : y).ifPresent(System.out::println);
    }

    // 如同{@link demo_sum_reduce} ，没有指定初始值及没有指定返回值类型
    public static void demo_sum_reduce2(List<Integer> num) {

        num.stream().reduce(Math::addExact).ifPresent(System.out::println);
    }

    // 使用reduce求和
    public static void demo_sum_reduce(List<Integer> num) {
        /**
         * T reduce(T identity, BinaryOperator<T> accumulator)
         *  reduce 接受两个参数:
         *        一个初始值，此值的类型即就是返回值类型，这里是0;
         *        一个 BinaryOperator<T> 来将两个元素结合起来产生一个新值，这里我们用的是lambda (a, b) -> a + b
         */
        Integer integer = num.stream().reduce(0, (a, b) -> a + b);
//        Math::addExact,
//        Math::subtractExact,
//        Math::multiplyExact
//        Math::floorDiv  如果参数的符号相同，那么floorDiv和/操作符的结果是一样的。如果参数的符号不同，则商为负，floorDiv返回小于或等于商的整数，/操作符返回最接近零的整数。
//        System.out.println(num.stream().reduce(0, Math::addExact));
        System.out.println(integer);
    }

    // 传统foreach求和
    public static void demo_sum_foreach(List<Integer> num) {
        int sum = 0;
        for (int x : num) {
            sum += x;
        }
        System.out.println(sum);
    }

}
