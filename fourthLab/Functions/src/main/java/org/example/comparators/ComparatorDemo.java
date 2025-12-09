package org.example.comparators;

import org.example.products.Product;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorDemo {
    public static <T extends Product> void sortGoods(T[] products, Comparator<Product> comparator) {
        if (products == null) {
            throw new IllegalArgumentException("Массив товаров не может быть null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Компаратор не может быть null");
        }
        Arrays.sort(products, comparator);
    }
}
