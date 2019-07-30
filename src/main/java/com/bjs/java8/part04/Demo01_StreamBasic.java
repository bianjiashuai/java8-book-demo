package com.bjs.java8.part04;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_StreamBasic {

    public static void main(String[] args) {
        getLowerCaloricDishesNamesInJDK7(Dish.menu).forEach(System.out::println);
        System.out.println("-----------------------------------");
        getLowerCaloricDishesNamesInJDK8(Dish.menu).forEach(System.out::println);
        System.out.println("-----------------------------------");
        System.out.println(Dish.menu.stream().collect(groupingBy(Dish::getType)));
    }

    public static List<String> getLowerCaloricDishesNamesInJDK7(List<Dish> dishes) {
        List<Dish> lowerDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowerDishes.add(dish);
            }
        }
        Collections.sort(lowerDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        List<String> result = new ArrayList<>();
        for (Dish dish : lowerDishes) {
            result.add(dish.getName());
        }

        return result;
    }

    public static List<String> getLowerCaloricDishesNamesInJDK8(List<Dish> dishes) {

        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
