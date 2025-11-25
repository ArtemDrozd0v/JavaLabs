package org.example.products;

import java.util.Objects;

public class WeightProduct extends Product {
    public WeightProduct(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightProduct weightProduct = (WeightProduct) o;
        return Objects.equals(getDescription(), weightProduct.getDescription()) &&
                Objects.equals(getName(), weightProduct.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getName());
    }

    @Override
    public String toString() {
        return "WeightProduct{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
