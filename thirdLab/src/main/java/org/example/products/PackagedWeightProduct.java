package org.example.products;

import java.util.Objects;

public class PackagedWeightProduct implements IPackagedProduct {
    private final WeightProduct product;
    private final double productWeight;
    private final org.example.products.Package pack;

    public PackagedWeightProduct(WeightProduct product, double productWeight, org.example.products.Package pack) {
        this.product = product;
        if (productWeight <= 0) {
            throw new IllegalArgumentException("Масса не может быть отрицательной");
        }
        this.productWeight = productWeight;
        this.pack = pack;
    }

    @Override
    public double getNetMass() {
        return productWeight;
    }

    @Override
    public double getBruttoMass() {
        return getNetMass() + pack.getWeight();
    }

    public WeightProduct getProduct() {
        return product;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public Package getPack() {
        return pack;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedWeightProduct that = (PackagedWeightProduct) o;
        return Double.compare(productWeight, that.productWeight) == 0 && Objects.equals(product, that.product) && Objects.equals(pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productWeight, pack);
    }

    @Override
    public String toString() {
        return "PackagedWeightProduct{" +
                "product=" + product +
                ", productWeight=" + productWeight +
                ", pack=" + pack +
                '}';
    }
}
