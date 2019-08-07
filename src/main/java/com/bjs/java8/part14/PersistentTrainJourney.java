package com.bjs.java8.part14;

import java.util.function.Consumer;

/**
 * @Description 持久化数据结构
 * @Author BianJiashuai
 */
public class PersistentTrainJourney {

    public static void main(String[] args) {
        TrainJourney tj1 = new TrainJourney(40, new TrainJourney(30, null));
        TrainJourney tj2 = new TrainJourney(20, new TrainJourney(50, null));

        TrainJourney append = append(tj1, tj2);
        visit(append, tj -> System.out.print(tj.price + " -> "));
        System.out.println();

        System.out.println("-----------------------------");
        TrainJourney link = link(tj1, tj2);
        visit(link, tj -> System.out.print(tj.price + " -> "));
        System.out.println();
    }

    static class TrainJourney {
        public int price;
        public TrainJourney onward;

        public TrainJourney(int price, TrainJourney onward) {
            this.price = price;
            this.onward = onward;
        }
    }

    // 会破坏原有线路
    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    // 不会破坏原有对象，因为到`b`之前的对象都是new出来的
    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    static void visit(TrainJourney tj, Consumer<TrainJourney> consumer) {
        if (tj != null) {
            consumer.accept(tj);
            visit(tj.onward, consumer);
        }
    }
}
