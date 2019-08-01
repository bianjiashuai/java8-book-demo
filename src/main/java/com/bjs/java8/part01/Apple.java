package com.bjs.java8.part01;

import lombok.*;

/**
 * @Description
 * @Author BianJiashuai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Apple {
    @NonNull
    private int weight;
    @NonNull
    private String name;
    private String color;
}