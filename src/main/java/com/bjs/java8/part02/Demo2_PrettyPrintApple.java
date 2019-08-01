package com.bjs.java8.part02;

import com.bjs.java8.part01.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo2_PrettyPrintApple {

    public interface AppleFormatter {
        String accept(Apple apple);
    }

    public static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + apple.getColor() + " apple";
        }
    }

    public static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
        }
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String outputStr = formatter.accept(apple);
            System.out.println(outputStr);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(120, "苹果1", "green"));
        inventory.add(new Apple(150, "苹果2", "red"));
        inventory.add(new Apple(135, "苹果3", "green"));
        inventory.add(new Apple(188, "苹果4", "blue"));
        inventory.add(new Apple(210, "苹果5", "green"));
        inventory.add(new Apple(190, "苹果6", "red"));
        prettyPrintApple(inventory, new AppleSimpleFormatter());
        System.out.println("----------------------------------------");
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}
