package org.example.products;

import java.util.Objects;

public class Package {
    private final String name;
    private final double weight;

    public Package(String name, double weight) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Отсутствует название упаковки");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Отрицательный вес упаковки");
        }
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package that = (Package) o;
        return Double.compare(weight, that.weight) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "ProductPackaging{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
