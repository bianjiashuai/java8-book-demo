package com.bjs.java8.part06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author BianJiashuai
 */
public class PrimeNumbersCollector implements Collector<
            Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{;
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (m, candidate) -> m.get(isPrime(m.get(true), candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (m1, m2) -> {
            m1.get(true).addAll(m2.get(true));
            m1.get(false).addAll(m2.get(false));
            return m1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static<T> List<T> takeWhile(List<T> list, Predicate<T> p) {
        int i = 0;
        for (T t : list) {
            if (!p.test(t)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    // 质数返回true, 否则返回false
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateSqrt = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= candidateSqrt).stream().noneMatch(p -> candidate % p == 0);
    }

    public static void main(String[] args) {
        int n = 100;
        IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector())
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
