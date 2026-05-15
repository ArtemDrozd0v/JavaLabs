package org.example;

@FunctionalInterface
public interface CustomPredicate<H1, H2, H3, A> {
    boolean apply(H1 h1, H2 h2, H3 h3, A age);
}
