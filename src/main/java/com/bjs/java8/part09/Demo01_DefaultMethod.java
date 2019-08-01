package com.bjs.java8.part09;

/**
 * @Description 接口中默认方法
 * @Author BianJiashuai
 */
public class Demo01_DefaultMethod {
    public static void main(String[] args) {
        C c = new C();
        c.hello();

        new D().hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface A1 {
    default void hello() {
        System.out.println("Hello from A1");
    }
}

interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {

}

class D implements A, A1 {
    @Override
    public void hello() {
        System.out.println("两个不相关的接口中同样的默认方法，这里面必须显示实现");
        // 如果想使用某个接口中的方法，使用如下方式即可.
        A.super.hello();
        A1.super.hello();
    }
}
