package org.example.products;

import java.util.List;
import java.util.Objects;

public class ProductBatch {
    private final List<IPackagedProduct> products;
    private final String description;

    public ProductBatch(String description, List<IPackagedProduct> products) {
        this.description = description;
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public List<IPackagedProduct> getProducts() {
        return products;
    }

    public double totalBruttoMass() {
        double sum = 0;
        for (IPackagedProduct prod : products) {
            sum += prod.getBruttoMass();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBatch that = (ProductBatch) o;
        return Objects.equals(products, that.products) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, description);
    }

    @Override
    public String toString() {
        return "ProductBatch{" +
                "products=" + products +
                ", description='" + description + '\'' +
                '}';
    }
}
