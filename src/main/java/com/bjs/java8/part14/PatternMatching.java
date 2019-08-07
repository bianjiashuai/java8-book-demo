package com.bjs.java8.part14;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description
 * @Author BianJiashuai
 */
public class PatternMatching {

    public static void main(String[] args) {
        simplify();

        Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e + " = " + result);
    }

    private static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binOpCase =
                (opName, left, right) -> {
                    if ("+".equals(opName)) {
                        if (left instanceof Number && ((Number) left).val == 0) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 0) {
                            return left;
                        }
                    }
                    if ("*".equals(opName)) {
                        if (left instanceof Number && ((Number) left).val == 1) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 1) {
                            return left;
                        }
                    }
                    return new BinOp(opName, left, right);
                };
        Function<Integer, Expr> numCase = val -> new Number(val);
        Supplier<Expr> defaultCase = () -> new Number(0);

        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = patternMatchExpr(e, binOpCase, numCase, defaultCase);
        if (match instanceof Number) {
            System.out.println("Number: " + match);
        } else if (match instanceof BinOp) {
            System.out.println("BinOp: " + match);
        }
    }

    private static Integer evaluate(Expr e) {
        Function<Integer, Integer> numCase = val -> val;
        Supplier<Integer> defaultCase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binOpCase =
                (opName, left, right) -> {
                    if ("+".equals(opName)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val + ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val + evaluate(left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val + evaluate(right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate(left) + evaluate(right);
                        }
                    }
                    if ("*".equals(opName)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val * ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val * evaluate(left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val * evaluate(right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate(left) * evaluate(right);
                        }
                    }
                    return defaultCase.get();
                };

        return patternMatchExpr(e, binOpCase, numCase, defaultCase);
    }

    static class Expr {
    }

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class BinOp extends Expr {
        String opName;
        Expr left, right;

        public BinOp(String opName, Expr left, Expr right) {
            this.opName = opName;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + " " + opName + " " + right + ")";
        }
    }

    static <T> T MyIf(boolean b, Supplier<T> trueCase, Supplier<T> falseCase) {
        return b ? trueCase.get() : falseCase.get();
    }

    interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(Expr e,
                                  TriFunction<String, Expr, Expr, T> binOpCase,
                                  Function<Integer, T> numCase, Supplier<T> defaultCase) {

        if (e instanceof BinOp) {
            return binOpCase.apply(((BinOp) e).opName, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof Number) {
            return numCase.apply(((Number) e).val);
        } else {
            return defaultCase.get();
        }
    }

}
