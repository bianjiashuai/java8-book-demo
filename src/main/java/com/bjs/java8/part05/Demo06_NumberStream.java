package com.bjs.java8.part05;

import static com.bjs.java8.part04.Dish.menu;

import com.bjs.java8.part04.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Description 数值流（三个原始类型特化流接口：IntStream、DoubleStream、LongStream）
 * 这些特化的原因并不在于流的复杂性，而是装箱造成的复杂性 —— 即类似int和Integer之间的效率差异
 * @Author BianJiashuai
 */
public class Demo06_NumberStream {

    public static void main(String[] args) {
//        demo_map_number();
//        demo_convert_boxed();
//        demo_optional_number();
//        demo_number_range();

//        pythagoreanTriples();

        // 【第一种】先生成所有对象，然后在进行筛选，需要使用double数组保证筛选正常执行
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        // 此处不能使用int[]数组，否则小数部分会被砍掉，导致下面过滤全部通过
                        .mapToObj(b -> new double[]{a * a, b * b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0))
                .limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


    }

    // 毕达哥拉斯三元数（勾股数）【第一种】先限制条件在生成对象，需要开两次平方
    public static void pythagoreanTriples() {
        Stream<int[]> data = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

//        List<int[]> ints = data.collect(Collectors.toList());
//        for (int[] a : ints) {
//            System.out.println(Arrays.toString(a));
//        }
        data.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    public static void demo_number_range() {
        /**
         * IntStream, LongStream
         * range(start, end)        生成start~end之间的数字，不含end
         * rangeClosed(start, end)  生成start~end之间的数字，含end
         */
        IntStream rangeNumbers = IntStream.range(1, 100);   // 生成1~100之间的数字（不包含100）
        System.out.println(rangeNumbers.count());
        IntStream rangeClosedNumbers = IntStream.rangeClosed(1, 100);
        System.out.println(rangeClosedNumbers.count());
        System.out.println(LongStream.range(1, 100).filter(i -> i % 2 != 0).count());
    }

    public static void demo_optional_number() {
        // OptionalDouble, OptionalLong
        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();  // 找到最大元素
        optionalInt.orElse(1);  // 如果没有最大值（流可能为空），显示提供一个默认最大值
    }

    public static void demo_convert_boxed() {
        // 在需要将数值范围装箱成为一个一般流时， boxed 尤其有用
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);    // 将Stream转换成数值流
        Stream<Integer> stream = intStream.boxed();     // 将数值流封装成Stream
    }

    public static void demo_map_number() {
        // 映射到数值流：将流转换为特化版本的常用方法是mapToInt、mapToDouble和mapToLong
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)    // 返回一个IntStream
                .sum();// 请注意，如果流是空的，sum 默认返回0。IntStream还支持其他的方便方法，如max、min、average等
        System.out.println(sum);
    }
}
