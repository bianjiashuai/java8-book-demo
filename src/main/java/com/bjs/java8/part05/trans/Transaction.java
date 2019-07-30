package com.bjs.java8.part05.trans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @Author BianJiashuai
 */
@Data
@AllArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
