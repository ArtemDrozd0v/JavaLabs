package org.example;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaDemo {
    public static final Function<String, Integer> stringLength = String::length;

    public static final Function<String, Character> firstChar = s -> {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return s.charAt(0);
    };

    public static final Predicate<String> checkSpaces = s -> !s.contains(" ");

    public static final Function<String, Integer> countWords = s -> {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return s.split(",").length;
    };

    public static final Function<Human, Integer> getHumanAge = Human::getAge;

    public static final BiPredicate<Human, Human> sameSurnames = (h1, h2) -> h1.getSurname().equals(h2.getSurname());

    public static final Function<Human, String> getFullName = h ->
            h.getSurname() + " " + h.getName() + " " + h.getPatronymic();

    public static final UnaryOperator<Human> makeOlder = h -> new Human(
            h.getSurname(),
            h.getName(),
            h.getPatronymic(),
            h.getAge() + 1,
            h.getGender()
    );

    public static final CustomPredicate<Human, Human, Human, Integer> ageCheck = (h1, h2, h3, maxAge) ->
            h1.getAge() < maxAge && h2.getAge() < maxAge && h3.getAge() < maxAge;

}
