package com.bjs.java8.part06;

import static com.bjs.java8.part04.Dish.Type;
import static com.bjs.java8.part04.Dish.menu;

import com.bjs.java8.part04.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 数据分组体验tt
 * @Author BianJiashuai
 */
public class Demo01_Group {
    public static void main(String[] args) {
//        old_method();
        // jdk8将食物按照类型分组
        Map<Type, List<Dish>> typeListMap = menu.stream().collect(Collectors.groupingBy(dish -> dish.getType()));
        System.out.println(typeListMap);
    }

    /**
     * jdk8以前将食物按照类型分组
     */
    public static void old_method() {
        Map<Type, List<Dish>> dishesByType = new HashMap<>();
        for (Dish dish : menu) {
            Type type = dish.getType();
            List<Dish> dishes = dishesByType.get(type);
            if (dishes == null) {
                dishes = new ArrayList<>(); // 没有该分类则创建该分类的容器
                dishesByType.put(type, dishes); // 将容器放入map中
            }
            dishes.add(dish); // 添加数据到容器中
        }
        System.out.println(dishesByType);
    }
}
