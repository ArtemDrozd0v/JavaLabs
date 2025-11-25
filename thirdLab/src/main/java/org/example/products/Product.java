package org.example.products;

import java.util.Objects;

public class Product {
    private final String name;
    private final String description;

    public Product(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Отсутствует название товара");
        }
        if (description == null) {
            throw new IllegalArgumentException("Отсутствует описание товара");
        }
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}