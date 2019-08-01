package com.bjs.java8.part08;

/**
 * @Description Lambda使用在`策略模式`上
 * @Author BianJiashuai
 */
public class Demo02_Strategy {

    public static void main(String[] args) {
        Validator numeric = new Validator(new IsNumeric());
        System.out.println(numeric.validate("aaa"));
        Validator lowerCase = new Validator(new IsAllLowerCase());
        System.out.println(lowerCase.validate("bbb"));

        System.out.println("------------------使用Lambda表达式,--------------------");

        // 使用Lambda表达式, 不需要在去自己写实现接口的类
        Validator numericL  = new Validator(s -> s.matches("\\d+"));
        System.out.println(numericL.validate("aaa"));
        Validator lowerCaseL = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(lowerCaseL.validate("bbb"));
    }
}

interface ValidationStrategy {
    boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}

class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}

class Validator {
    private  ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s) {
        return validationStrategy.execute(s);
    }
}