package com.bjs.java8.part05.trans;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author BianJiashuai
 */
public class TestTrans {

    public static void main(String[] args) {

        Trader zs = new Trader("张三", "北京");
        Trader ls = new Trader("李四", "上海");
        Trader ww = new Trader("王五", "北京");
        Trader zl = new Trader("赵六", "北京");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(zl, 2018, 300),
                new Transaction(zs, 2019, 1000),
                new Transaction(zs, 2018, 400),
                new Transaction(ls, 2019, 710),
                new Transaction(ls, 2019, 700),
                new Transaction(ww, 2019, 950)
        );

        List<Trader> traders = Arrays.asList(ls, ww, zl, zs);

        // (1) 找出2018年发生的所有交易，并按交易额排序（从低到高）。
        transactions.stream()                                           // 获取此集合作为源的顺序Stream
                .filter(t -> t.getYear() == 2018)                       // 过滤只获取2018年
                .sorted(Comparator.comparing(Transaction::getValue))    // 按照交易额从低到高
                .forEach(System.out::println);                          // 打印
        printSplitLine();

        // (2) 交易员都在哪些不同的城市工作过？
        traders.stream()
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
        System.out.println("*******另一种********");
        Set<String> collect = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet());   // 使用toSet可以自动去重
        System.out.println(collect);
        printSplitLine();

        // (3) 查找所有来自于北京的交易员，并按姓名排序。
        traders.stream()
                .filter(t -> "北京".equals(t.getCity()))
                .map(Trader::getName)
                .sorted()
                .forEach(System.out::println);
        System.out.println("*******另一种********");
        List<String> bjNames = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "北京".equals(t.getCity()))
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(bjNames);
        printSplitLine();
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> names = traders.stream()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(names);
        System.out.println("*******另一种********");
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(traderNames);
        printSplitLine();

        // (5) 有没有交易员是在上海工作的？
        Optional<Trader> optional = traders.stream()
                .filter(t -> "上海".equals(t.getCity()))
                .findAny();
        System.out.println(optional.isPresent());
        System.out.println("*******另一种********");
        System.out.println(transactions.stream().anyMatch(t -> "上海".equals(t.getTrader().getCity())));
        printSplitLine();

        // (6) 打印生活在北京的交易员的所有交易额。
        transactions.stream()
                .filter(t -> "北京".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        printSplitLine();

        // (7) 所有交易中，最高的交易额是多少？
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
        printSplitLine();

        // (8) 找到交易额最小的交易
        Optional<Transaction> minValueTrans = transactions.stream()
                .filter(t -> t.getValue() == transactions.stream()
                        .map(Transaction::getValue)
                        .sorted()
                        .findFirst().get()
                ).findFirst();
        System.out.println(minValueTrans.get());
        System.out.println("*******另一种********");
        System.out.println(transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1)
                .get());
    }

    private static void printSplitLine() {
        System.out.println("------------------------------------------------------------");
    }
}
