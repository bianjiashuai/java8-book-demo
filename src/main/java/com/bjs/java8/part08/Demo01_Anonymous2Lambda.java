package com.bjs.java8.part08;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_Anonymous2Lambda {

    public static void main(String[] args) {
//        demo_anonymous_lambda();

        /**
         * 将这种匿名类转换为Lambda表达式时，就导致了一种晦涩的方法调用，因为 Runnable和Task 都是合法的目标类型
         *  doSomething(Runnable) 和doSomething(Task)都匹配该类型
         */
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!!");
            }
        });

        // 对 Task 尝试使用显式的类型转换来解决这种模棱两可的情况
        doSomething((Task) () -> System.out.println("Danger danger!!!"));
    }

    // 匿名类和Lambda的基本区别
    public static void demo_anonymous_lambda() {
        /**
         * 匿名类和Lambda表达式中的 this 和 super 的含义是不同的。
         * 在匿名类中， this 代表的是类自身，但是在Lambda中，它代表的是包含类。
         * 其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不能（它们会导致编译错误），
         * 譬如下面这段代码：
         */
        int a = 10;
        Runnable r = () -> {
//            int a = 2;  // 这行会编译错误，因为有重名的属性
            System.out.println(a);
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;  // 正常编译
                System.out.println(a);
            }
        };
    }

    public static void doSomething(Runnable r) {
        r.run();
    }
    public static void doSomething(Task t) {
        t.execute();
    }

}

interface Task {
    void execute();
}
