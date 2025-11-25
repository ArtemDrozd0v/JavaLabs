package org.example.products;

import java.util.Objects;

public class PackagedPieceProduct implements IPackagedProduct {
    private final PieceProduct product;
    private final int quantity;
    private final org.example.products.Package pack;

    public PackagedPieceProduct(PieceProduct product, int quantity, org.example.products.Package pack) {
        this.product = product;
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
        this.quantity = quantity;
        this.pack = pack;
    }

    public PieceProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Package getPack() {
        return pack;
    }

    @Override
    public double getNetMass() {
        return product.getPieceWeight() * quantity;
    }

    @Override
    public double getBruttoMass() {
        return getNetMass() + pack.getWeight();
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedPieceProduct that = (PackagedPieceProduct) o;
        return quantity == that.quantity && Objects.equals(product, that.product) && Objects.equals(pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, pack);
    }

    @Override
    public String toString() {
        return "PackagedPieceProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", pack=" + pack +
                '}';
    }
}
