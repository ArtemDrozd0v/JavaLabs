package org.example.comparators;

import org.example.products.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Объекты null");
        }

        int nameComparison = p1.getName().compareTo(p2.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        return p1.getDescription().compareTo(p2.getDescription());
    }
}
