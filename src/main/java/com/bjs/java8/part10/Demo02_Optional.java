package com.bjs.java8.part10;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

/**
 * @Description Optional整合
 * @Author BianJiashuai
 */
public class Demo02_Optional {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

//        assertEquals(5, readDuration(props, "a"));
//        assertEquals(0, readDuration(props, "b"));
//        assertEquals(0, readDuration(props, "c"));
//        assertEquals(0, readDuration(props, "d"));
        assertEquals(5, readDurationOptional(props, "a"));
        assertEquals(0, readDurationOptional(props, "b"));
        assertEquals(0, readDurationOptional(props, "c"));
        assertEquals(0, readDurationOptional(props, "d"));
    }

    public static int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (name != null) {
            try {
                int i = Integer.parseInt(value);
                return i;
            } catch (NumberFormatException ex) {}
        }
        return 0;
    }

    public static int readDurationOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(s -> stringToInt(s))
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
