package com.bjs.java8.part08;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description lambda 观察者模式
 * @Author BianJiashuai
 */
public class Demo03_Observer {
    public static void main(String[] args) {
        Feed f = new Feed();
//        demo_default(f);

        f.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("纽约时报: " + tweet);
            }
        });

        f.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("卫报: " + tweet);
            }
        });

        f.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("世界报: " + tweet);
            }
        });
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");

    }

    public static void demo_default(Feed f) {
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
    }
}

interface Observer {
    void notify(String tweet);
}

interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers(String tweet);
}

class Feed implements Subject {

    List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(observer -> observer.notify(tweet));
    }
}

class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("纽约时报: " + tweet);
        }
    }
}

class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("卫报: " + tweet);
        }
    }
}

class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("世界报: " + tweet);
        }
    }
}
