package org.example;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaRunner {
    public static <T, R> R run(Function<T, R> lambda, T arg) {
        return lambda.apply(arg);
    }

    public static <T> boolean run(Predicate<T> lambda, T arg) {
        return lambda.test(arg);
    }

    public static <T, U> boolean run(BiPredicate<T, U> lambda, T arg1, U arg2) {
        return lambda.test(arg1, arg2);
    }

    public static <H1, H2, H3, A> boolean run(CustomPredicate<H1, H2, H3, A> lambda, H1 h1, H2 h2, H3 h3, A maxAge) {
        return lambda.apply(h1, h2, h3, maxAge);
    }
}
