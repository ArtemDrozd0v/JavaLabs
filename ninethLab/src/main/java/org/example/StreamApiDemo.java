package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class StreamApiDemo extends LambdaDemo {
    public static final UnaryOperator<List<?>> removeNulls = list ->
            list.stream().
                    filter(Objects::nonNull).
                    collect(Collectors.toList());

    public static final Function<Set<Integer>, Long> countPositive = set ->
            set.stream().
                    filter(n -> n > 0).
                    count();

    public static final Function<List<?>, List<?>> lastTree = list ->
            list.stream()
                    .skip(Math.max(0, list.size() - 3))
                    .collect(Collectors.toList());

    public static final Function<List<Integer>, Integer> firstEven = list ->
            list.stream()
                    .filter(n -> n % 2 == 0)
                    .findFirst()
                    .orElse(null);

    public static final Function<int[], List<Integer>> uniqueSquares = array ->
            Arrays.stream(array)
                    .map(n -> n * n)
                    .distinct()
                    .boxed()
                    .collect(Collectors.toList());

    public static final UnaryOperator<List<String>> filteredList = list ->
            list.stream()
                    .filter(s -> s != null && !s.isEmpty())
                    .sorted()
                    .collect(Collectors.toList());

    public static final Function<Set<String>, List<String>> setToList = set ->
            set.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

    public static final Function<Set<Integer>, Integer> sumSquares = set ->
            set.stream()
                    .mapToInt(n -> n * n)
                    .sum();

    public static final Function<Collection<Human>, Integer> maxAge = list ->
            list.stream()
                    .mapToInt(Human::getAge)
                    .max()
                    .orElse(0);

    public static final Function<Collection<Human>, List<Human>> sortPeople = col ->
            col.stream()
                    .sorted(Comparator.comparing(Human::getGender).thenComparing(Human::getAge))
                    .collect(Collectors.toList());
}


