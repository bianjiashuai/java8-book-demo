package com.bjs.java8.part01;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo1_Test {

    public static void main(String[] args) {
//        demo1();
        demo2();

    }

    /**
     * 获取隐藏文件
     */
    private static void demo2() {
        // 普通操作
//        new File(".").listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isHidden();
//            }
//        });
        // Lamdba
//        new File(".").listFiles(pathname -> pathname.isHidden());

        // java8 方法引用
        new File(".").listFiles(File::isHidden);
    }

    /**
     * 按照引用对象指定字段排序
     */
    private static void demo1() {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(360, "1号"));
        list.add(new Apple(364, "2号"));
        list.add(new Apple(367, "3号"));
        list.add(new Apple(362, "4号"));

        System.out.println(list);
//        list.sort(Comparator.comparingInt(Apple::getWeight));
//        Collections.sort(list, Comparator.comparingInt(Apple::getWeight));
        list.sort(Comparator.comparing(Apple::getName));

        System.out.println(list);
    }
}
